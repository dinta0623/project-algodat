// Kelas Grpah digunakan sebagai wrapper atas vertex-vertex dan dibentuk atas pola-pola yang telah terdefinisi
public class Graph {
    public String name;
    public DLinkedList<Vertex> points;

    // method ini digunakan untuk inisialisasi kelas Graph yang berisi parameter
    // nama untuk disimpan kedalam property @this.name
    // selai itu method ini juga digunakan untuk menginisialisasi doubly linkedlist
    // vertex pada kelas
    public Graph(String name) {
        this.name = name;
        this.points = new DLinkedList<Vertex>();
    }

    // method void add() berfungsi untuk menambahkan vertex ke dalam doubly
    // linkedlist pada
    // graph
    public void add(Vertex vertex) {
        this.points.insert(vertex);
    }

    // public void setConnection(String from, String to, int length) {
    // Node<Vertex> fromNode = this.points.getNode(new Vertex(from));
    // fromNode.getData().edges.insert(new Edge(length, this.points.getNode(new
    // Vertex(to))));
    // }

    // method void setConnection() digunakan untuk membuat koneksi antar vertex (dua
    // arah) secara otomatis dengan parameter awal dan tujuan berupa vertex, juga
    // parameter @length untuk menentukan panjang property simpul
    public void setConnection(Vertex from, Vertex to, int length) {
        this.points.getNode(from).getData().edges.insert(new Edge(length, this.points.getNode(to)));
        this.points.getNode(to).getData().edges.insert(new Edge(length, this.points.getNode(from)));
    }

    // method void printoutAdjlist() digunakan untuk melakukukan cetakan ke console
    // berupa Adjenct list dari pola graph yang tersimpan beserta simpul-simpulnya
    public void printoutAdjlist() {
        System.out.println("\n- Adjenct List : ");
        Node<Vertex> shifter = this.points.getTail();
        if (shifter == null)
            System.out.print("-");
        else
            System.out.println();
        while (shifter != null) {
            System.out.print("[" + shifter.getData().name + "]");
            Node<Edge> edgeShifter = shifter.getData().edges.getTail();
            while (edgeShifter != null) {
                System.out.print(" -> " + edgeShifter.getData().destination.getData());
                edgeShifter = edgeShifter.getPrev();
            }
            System.out.println();
            shifter = shifter.getPrev();
        }
        System.out.println();
    }

    // method void printoutDPS() digunakan untuk mencetak hubungan yang ada pada
    // setiap vertex pada graph sehingga diketahui ukuran dari setiap hubungan antar
    // vertex
    public void printoutDPS(Node<Vertex> shifter, DLinkedList<String> isVisited) {
        if (shifter == null && isVisited == null) {
            shifter = this.points.getTail();
            System.out.println("\n\n" + this.name);
        }
        if (isVisited == null)
            isVisited = new DLinkedList<String>();

        if (shifter != null) {
            shifter.getData().printoutConnection();
            isVisited.insert(shifter.getData().toString());
            Node<Edge> adjShift = shifter.getData().edges.getTail();
            while (adjShift != null) {
                if (isVisited.getNode(adjShift.getData().destination.getData().toString()) == null) {
                    printoutDPS(adjShift.getData().destination, isVisited);
                }
                adjShift = adjShift.getPrev();
            }
        }
    }

    // method boolean isPrimLAready() digunakan untuk mengecek, apakah vertex yang
    // ada pada graph sudah semuanya dikunjungi atau belum
    boolean isPrimLAready() {
        Node<Vertex> tmp = this.points.getTail();
        while (tmp != null) {
            if (!tmp.getData().isVisited) {
                return false;
            }
            tmp = tmp.getPrev();
        }
        return true;
    }

    // method DLinkedList<Edge> runPrim() adalah fungsi utama yang akan menjalankan
    // algoritma prim untuk mencari jalur effisien pada graph. Mengembalikan nilai
    // DLinkedList<Edge> yang akan digunakan pada method lain untuk dimanipulasi
    // secara kustom kembali. Method ini selain menjalankan algoritma prim, juga
    // mencetak hasil ke console hasil dari pola graph baru yang terbentuk
    DLinkedList<Edge> runPrim() {
        DLinkedList<Edge> unAddedEgde = new DLinkedList<>();
        DLinkedList<Edge> addedEdge = new DLinkedList<>();
        DLinkedList<Vertex> V = new DLinkedList<>();
        if (this.points.length > 0) {
            this.points.getTail().getData().isVisited = true;
            // V.insert(this.points.getTail().getData());
        }
        while (!isPrimLAready()) {
            Node<Vertex> sVertex = this.points.getTail();
            Node<Vertex> nextVertex = sVertex;
            Node<Edge> nextEdge = null;
            while (sVertex != null) {
                Node<Edge> sEdge = sVertex.getData().edges.getTail();
                nextEdge = null;
                while (sEdge != null) {
                    if (!sEdge.getData().isIncluded && !sEdge.getData().destination.getData().isVisited
                            && (nextEdge == null || sEdge.getData().length < nextEdge.getData().length)) {
                        nextEdge = sEdge;
                        nextVertex = sEdge.getData().destination;
                    }
                    sEdge.getData().origin = sVertex;
                    unAddedEgde.insert(sEdge.getData());
                    sEdge = sEdge.getPrev();
                }
                sEdge = unAddedEgde.getTail();
                while (sEdge != null) {
                    if (!sEdge.getData().isIncluded && !sEdge.getData().destination.getData().isVisited
                            &&
                            ((sVertex != nextVertex && sEdge.getData().destination == nextVertex)
                                    || sVertex == nextVertex)
                            && (nextEdge == null || sEdge.getData().length < nextEdge.getData().length)) {
                        nextEdge = sEdge;
                        nextVertex = sEdge.getData().destination;
                    }
                    sEdge = sEdge.getPrev();
                }
                // A // C
                if (nextEdge != null) {
                    nextEdge.getData().isIncluded = true;
                    nextVertex.getData().isVisited = true;

                    System.out
                            .println(nextEdge.getData().origin.getData() + "---" + nextEdge.getData().length + "---"
                                    + nextVertex.getData());

                    addedEdge.insert(nextEdge.getData());
                    // B -> A -> C
                    // V.insert(nextVertex.getData());

                    sVertex = nextVertex;
                } else {
                    sVertex = null;
                }
            }
        }
        return addedEdge;
    }

}
