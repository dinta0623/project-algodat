// class Edge digunakan untuk meyimpan jalur dari setiap simpul/vertex
public class Edge {
    // property length digunakan untuk mengetahui length edge yang dimaksud
    public int length;
    // property origin digunakan untuk menyimpan referensi vertex asal
    public Node<Vertex> origin = null;
    // property destination digunakan untuk menyimpan referensi vertex yang dituju
    public Node<Vertex> destination = null;
    // property isIncluded digunakan untuk mengetahui apakah edge sudah dikunjungi
    // atau belum
    public boolean isIncluded = false;

    // contructor dari Edge yang berisi parameter @length yang akan mengisi @length
    // dari
    // Edge
    Edge(int length) {
        this.length = length;
    }

    // contructor dari Edge yang berisi parameter bukah hanya length, tetapi juga
    // destination untuk simplicity ketika membuat Edge baru
    Edge(int length, Node<Vertex> destination) {
        this.length = length;
        this.destination = destination;
    }
}
