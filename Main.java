
// Digunakan untuk menggunakan Exception pada java, handle error
import java.io.IOException;
// Digunakan untuk melakukan scan terhadap input yang dimasukkan di console
import java.util.Scanner;

// Class Main adalah class core dari aplikasi
public class Main {
    // Mendefiniskan variabel scanner dan konstan yang berkaitan dengan aplikasi
    // pada kelas
    static String end = "\n---Terima Kasih---\n";
    static String name = "Graph Program";
    static int choice;
    static String choice_str;
    static Scanner input;
    static Graph Store = new Graph(name);

    // void clear() digunakan untuk melakukan pembersihan console
    static void clear() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Runtime.getRuntime().exec("clear");
        if (input != null)
            input.nextLine();
    }

    // method void calculate() digunakan untuk melakukan kalkulasi terhadap hasil
    // yang di keluarkan oleh method runPrim() pada class graph dan memanipulasi
    // datanya untuk diolah kembali menjadi informasi, seperti total panjang dari
    // edges terpilih
    static void calculate() {
        if (!Store.points.isEmpty()) {
            // buat vertex baru

            System.out.println("\n- Kalkulasi prim's algorithm :\n");
            Graph newGraph = new Graph("Prim");
            DLinkedList<Edge> edgesFiltered = Store.runPrim();

            System.out.print("\n- Jalur Efisien : ");
            Node<Edge> temp = edgesFiltered.getTail();
            int bestCounter = 0;
            while (temp != null) {
                Vertex newVertexOrigin = new Vertex(temp.getData().origin.getData().name);
                Vertex newVertexSource = new Vertex(temp.getData().destination.getData().name);
                if (newGraph.points.getNode(newVertexOrigin) == null)
                    newGraph.add(newVertexOrigin);
                if (newGraph.points.getNode(newVertexSource) == null)
                    newGraph.add(newVertexSource);
                newGraph.setConnection(newVertexOrigin, newVertexSource, temp.getData().length);
                bestCounter += temp.getData().length;
                if (temp.getPrev() == null) {
                    System.out.println(bestCounter);
                }
                temp = temp.getPrev();
            }
            newGraph.printoutAdjlist();
        }
        main(null);
    }

    // method void example() digunakan untuk melakukan penambahan pola default
    // terhadap pola terkini. Main ideanya adalah untuk menunjukkan contoh pola yang
    // sudah ditentukan sebelumnya
    static void example() {
        // Vertex Plug0 = new Vertex("Plug0");
        // Vertex Plug1 = new Vertex("Plug1");
        // Vertex Plug2 = new Vertex("Plug2");
        // Vertex Plug3 = new Vertex("Plug3");
        // Vertex Plug4 = new Vertex("Plug4");
        // Vertex Plug5 = new Vertex("Plug5");
        // Vertex Plug6 = new Vertex("Plug6");
        // Vertex Plug7 = new Vertex("Plug7");
        // Vertex Plug8 = new Vertex("Plug8");

        // Store.add(Plug0);
        // Store.add(Plug1);
        // Store.add(Plug2);
        // Store.add(Plug3);
        // Store.add(Plug4);
        // Store.add(Plug5);
        // Store.add(Plug6);
        // Store.add(Plug7);
        // Store.add(Plug8);

        // Store.setConnection(Plug0, Plug1, 4);
        // Store.setConnection(Plug0, Plug7, 8);

        // Store.setConnection(Plug1, Plug7, 11);
        // Store.setConnection(Plug1, Plug2, 8);

        // Store.setConnection(Plug7, Plug8, 7);
        // Store.setConnection(Plug7, Plug6, 1);

        // Store.setConnection(Plug6, Plug8, 6);
        // Store.setConnection(Plug6, Plug5, 2);

        // Store.setConnection(Plug8, Plug2, 2);

        // Store.setConnection(Plug2, Plug3, 7);
        // Store.setConnection(Plug2, Plug5, 4);

        // Store.setConnection(Plug5, Plug3, 14);
        // Store.setConnection(Plug5, Plug4, 10);

        // Store.setConnection(Plug4, Plug3, 9);

        Vertex PlugA = new Vertex("PlugA");
        Vertex PlugB = new Vertex("PlugB");
        Vertex PlugC = new Vertex("PlugC");
        Vertex PlugD = new Vertex("PlugD");
        Vertex PlugE = new Vertex("PlugE");

        Store.add(PlugA);
        Store.add(PlugB);
        Store.add(PlugC);
        Store.add(PlugD);
        Store.add(PlugE);

        Store.setConnection(PlugB, PlugA, 2);
        Store.setConnection(PlugB, PlugC, 2);
        Store.setConnection(PlugB, PlugE, 5);

        Store.setConnection(PlugA, PlugC, 3);

        Store.setConnection(PlugC, PlugD, 1);
        Store.setConnection(PlugC, PlugE, 1);

        Store.printoutDPS(null, null);
        Store.printoutAdjlist();
        main(null);
    }

    // method void returnMenu(String msg) berfungsi untuk melakukan navigasi kembali
    // ke menu utama dengan kustomisasi pesan yang dapat dicetak sebelum melakukan
    // navigasi
    static void returnMenu(String msg) {
        System.out.print("\n- ");
        if (msg != null) {
            System.out.print(msg);
            System.out.print(" ");
        } else
            System.out.print("Tidak ditemukan. ");
        System.out.println("(kembali ke menu)");
        Store.printoutAdjlist();
        main(null);
    }

    // method void addConnection() digunakan sebagai handler untuk program melakukan
    // penambahan koneksi secara dinamis
    static void addConnection() {
        System.out.print("\n- Pilihan 2, (angka 0 untuk kembali) Masukkan nama titik yang dicari : ");
        choice_str = input.next();
        System.out.println(choice_str);
        input.nextLine();

        Node<Vertex> targetFrom = Store.points.getNode(new Vertex(choice_str.toLowerCase().replaceAll("\\s", "-")));
        if (choice_str.trim().equals("0") || choice_str == null || targetFrom == null) {
            returnMenu(choice_str.trim().equals("0") ? "" : null);
            return;
        }
        System.out.print("\n- (angka 0 untuk kembali) Masukkan nama titik tujuan : ");

        choice_str = input.next();
        System.out.println(choice_str);
        input.nextLine();
        Node<Vertex> target = Store.points.getNode(new Vertex(choice_str.toLowerCase().replaceAll("\\s", "-")));
        if (choice_str.trim().equals("0") || choice_str == null || targetFrom == null) {
            returnMenu(choice_str.trim().equals("0") ? "" : "Titik awal dengan tujuan sama!.");
            return;
        }
        System.out.print("\n- Masukkan bobot koneksi (ex. 5) : ");
        try {
            int bobot = input.nextInt();
            Store.setConnection(targetFrom.getData(), target.getData(), bobot);
            addConnection();
        } catch (Exception e) {
            returnMenu("Bobot tidak terdefinisi!");
        }
    }

    // method void addVertex() digunakan sebagai handler untuk program melakukan
    // penambahan vertex pada kelas graph secara dinamis
    static void addVertex() {
        System.out.print("\n- Pilihan 1, (angka 0 untuk kembali) masukkan nama titik baru : ");
        choice_str = input.nextLine();

        if (choice_str.trim().equals("0") || choice_str == null) {
            returnMenu(null);
            return;
        }
        // choice_str.replaceAll("\\s", "")
        Vertex newPoint = new Vertex(choice_str.toLowerCase().replaceAll("\\s", "-"));
        if (Store.points.getNode(newPoint) != null) {
            returnMenu("Nama titik sudah pernah ditambahkan!.");
            return;
        }
        Store.add(newPoint);
        addVertex();
    }

    // method void main(String args[]) adalah method utama yang akan menjadi basis
    // dari pada program, menjalankan keseluruhan method yang ada
    public static void main(String args[]) {
        input = new Scanner(System.in);
        System.out.println("\nProgram Penentu Jalur Efisien Berdasarkan Titik Tertentu Menggunakan Algoirtma Prim");
        System.out.println("- Pilihan Menu :");
        System.out.println("1.  Tambah Titik (simultan)");
        System.out.println("2.  Tambah Koneksi");
        System.out.println("3.  Setup Default Kasus");
        System.out.println("4.  Kalkulasi");
        System.out.println("5.  Lihat Adjenct List");
        System.out.println("6.  Reset");
        System.out.println("Any.Exit");
        System.out.print("- Masukkan Pilihan Anda : ");
        choice_str = input.next();

        try {
            choice = Integer.parseInt(choice_str);
            clear();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Anda memilih menu 1");
                    addVertex();
                    break;
                case 2:
                    System.out.println("Anda memilih menu 2");
                    addConnection();
                    break;
                case 3:
                    System.out.println("Anda memilih menu 3");
                    example();
                    break;
                case 4:
                    System.out.println("Anda memilih menu 4");
                    calculate();
                    break;
                case 5:
                    System.out.println("Anda memilih menu 5");
                    if (!Store.points.isEmpty())
                        Store.printoutAdjlist();
                    main(null);
                    break;
                case 6:
                    System.out.println("Anda memilih menu 6");
                    Store = new Graph(name);
                    main(null);
                    break;
                default:
                    System.out.println(end);
                    System.exit(0);
                    break;
            }
        } catch (Exception e) {
            System.out.println(end);
            System.exit(0);
        }
    }

}