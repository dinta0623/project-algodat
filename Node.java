// Class memiliki definisi generic sebagai basis tipe data yang disimpan
public class Node<T> {
    // dibuat private agar tidak dapat diakses langsung
    // tipe data @data berdasarkan Generic T
    private T data;
    // tipe data @next berdasarkan Generic Node<T> sebagai pointer untuk menyimpan
    // node
    private Node<T> next;
    // tipe data @prev berdasarkan Generic Node<T> sebagai pointer untuk menyimpan
    // node
    private Node<T> prev;

    // &constructor ketika Node di inisialiasasi
    Node(T data) {
        // mengisi data ketika inisialisasi
        this.data = data;
        // buat @this.next terinisialisasi secara default sebagai null
        this.next = null;
        // buat @this.prev terinisialisasi secara default sebagai null
        this.prev = null;
    }

    // &getNext untuk mendapatkan @this.next karena private
    Node<T> getNext() {
        return this.next;
    }

    // &setNext untuk memperbarui @this.next karena private
    Node<T> setNext(Node<T> payload) {
        this.next = payload;
        return this.next;
    }

    // &getPrev untuk mendapatkan @this.prev karena private
    Node<T> getPrev() {
        return this.prev;
    }

    // &setPrev untuk memperbarui @this.prev karena private
    Node<T> setPrev(Node<T> payload) {
        this.prev = payload;
        return this.prev;
    }

    // &getData untuk memperbarui @this.prev karena private
    T getData() {
        return this.data;
    }

    // &setData untuk memperbarui @this.data karena private
    T setData(T payload) {
        this.data = payload;
        return this.data;
    }
}
