// class Vertex digunakan untuk meyimpan vertex pada suatu graph
public class Vertex {
    // property nama digunakan untuk mengetahui nama vertex yang dimaksud
    public String name;
    public DLinkedList<Edge> edges;
    // setiap vertex menyimpan referensi simpulnya terhadap vertex lain sebagai
    // sebuah list
    public boolean isVisited = false;

    // contructor dari Vertex yang berisi parameter nama yang akan mengisi nama dari
    // Vertex
    public Vertex(String name) {
        this.name = name;
        this.edges = new DLinkedList<Edge>();
    }

    // mengembalikan nama dari vertex jika secara langsung vertex di print
    // menggunakan perintah System.out
    public String toString() {
        return this.name;
    }

    // mengembalikan semua nama dari edges jika secara langsung vertex di print
    // semua datanya
    public String toStringAll() {
        return this.edges.toString();
    }

    // fungsi void printoutConnection() ini untuk melakukan cetak hubungan vertex
    // terhadap semua edgesnya (simpul)
    public void printoutConnection() {
        Node<Edge> shifter = this.edges.getTail();
        while (shifter != null) {
            System.out.printf("\n--> %s --- %s --- %s", this.name, shifter.getData().length,
                    shifter.getData().destination.getData().toString());
            shifter = shifter.getPrev();
        }
        System.out.println();
    }

}
