import java.util.Objects;

// Class memiliki definisi type sebagai basis tipe data yang disimpan
public class DLinkedList<T> {
    // @head bertipe datakan class Node dengan Type K sesuai keinginan pengguna
    private Node<T> head;
    public int length;

    // &constructor memastikan bahwa head ketika sebuah instansi menginisialisasi
    // class, nilai head dalam keaadaan bersih (null)
    public DLinkedList() {
        // @head jadi null
        this.head = null;
    }

    // &constructor kedua memastikan bahwa head ada minimal satu data ketika
    // inisialisasi
    public DLinkedList(T payload) {
        // menggunakan method insert untuk memasukkan data dari parameter payload
        this.insert(payload);
    }

    // &isEmpty untuk mengecek head apakah null atau tidak
    public boolean isEmpty() {
        // jika null akan return true
        if (this.head == null) {
            return true;
        }
        // selain daripada itu, maka false
        return false;
    }

    // &getHead dibuat agar instance yang menggunakan kelas DLinkedList dapat
    // mengakses headnya langsung (keperluan printout di class Main)
    public Node<T> getHead() {
        // mengembalikan head pada class
        return this.head;
    }

    // &getTail dibuat agar data awal bisa didapatkan pointernya
    public Node<T> getTail() {
        // mengembalikan head pada class
        Node<T> current = this.head;
        // #while akan terus berjalan selama data @current tidak sama dengan null
        while (current != null && current.getNext() != null) {
            // pointer @current akan berjalan maju
            current = current.getNext();
        }
        // nilai kembalian berupa pointer paling akhir
        return current;
    }

    // &getNode dibuat agar data awal bisa didapatkan index urutannya
    public Node<T> getNode(int index) {
        if (!this.isEmpty()) {
            // mengembalikan head pada class
            Node<T> current = this.head;
            // initial index untuk mencari berdasarkan index
            int idx = 0;

            // #while akan terus berjalan selama maju
            while (current.getNext() != null) {
                // memajukan @current agar berjalan menuju tail
                current = current.getNext();
            }

            // #while akan terus berjalan selama mundur
            while (current != null && index != idx) {
                // memajukan @current agar berjalan menuju head
                current = current.getPrev();
                // menghitung @idx untuk mencari index pada node
                idx++;
            }
            // mengembalikan data @current sebagai Node<T> generic
            return current;
        }
        // memberikan nilai default sebagai null jika tidak terdapat data pada linked
        // list
        return null;
    }

    // method getNode(T At) digunakan untuk mengembalikan node yang sesuai dengan
    // term @At yang ada pada parameter
    Node<T> getNode(T At) {
        if (this.isEmpty())
            return this.getTail();
        Node<T> current = this.head;
        while (current != null) {
            if (Objects.equals(At.toString().toLowerCase(), current.getData().toString().toLowerCase()))
                return current;
            current = current.getNext();
        }
        return null;
    }

    // &insert untuk menambah head dan meneruskan pointer
    // @data adalah parameter berisi data aktual
    public void insert(T data) {
        if (data != null) {
            // inisialisasi data baru di @newNode
            Node<T> newNode = new Node<T>(data);
            newNode.setNext(this.head);
            // #if akan mengecek jika &isEmpty true atau false
            if (!this.isEmpty()) {
                // jika tidak berarti @head selanjutnya diisi agar @head.prev terhubung dengan
                // @newNode
                this.head.setPrev(newNode);
            }
            // set @head menjadi @newNode agar data baru masuk ke barisan
            this.head = newNode;
            // menambah dan menyimpan ukuran dari linkedlist
            this.length++;
        }
    }

    // &remove dapat menghapus data head
    void remove() {
        // #if akan mengecek jika &isEmpty true atau false
        if (!this.isEmpty()) {
            // @this.head masih bernilai null <- @this.head <- @this.head.next
            // tugasnya menggeser head ke head selanjutnya
            // @this.head akan diisi dengan next sehingga : @this.head <- @this.head.next ->
            // @this.head.next.next
            this.head = this.head.getNext();
            // @this.head.prev dibuat null sehingga : null <- @this.head.next ->
            // @this.head.next.next
            this.head.setPrev(null);
            this.length--;
        }
    }

    // &removeAt dibuat untuk return Node, agar debugging lebih mudah
    // parameter @data adalah target yang akan dihilangkan
    Node<T> removeAt(T data) {
        /*
         * jadi anggap parameter data adalah pulpy
         */

        // #if akan mengecek jika &isEmpty true atau false
        if (!this.isEmpty()) {
            // @temp untuk manipulasi data next nantinya
            // @current berisi head, untuk dimanipulasi
            Node<T> current = this.head, temp = null, removed = null;
            // #while akan terus berjalan selama data @current tidak sama dengan @data yang
            // akan dihapus
            while (current != null && data != current.getData()) {
                // meneruskan @current ke pointer selanjutnya
                current = current.getNext();
            }
            // @current akan berisi pulpy dan
            // kondisi dilakukan jika memang terdapat @data
            // yang dimaksud (pulpy)
            if (current != null) {
                removed = current;
                /*
                 *
                 * contoh :
                 * 
                 * @current : sedaap <- pulpy -> kopikap
                 * 
                 * @current.next : pulpy <- kopikap ->
                 * nabati
                 * 
                 * @current.prev : indomie <- sedaap -> pulpy
                 * 1
                 */

                // @temp akan terisi kopikap (@current.next) yang memiliki : pulpy <- kopikap ->
                // nabati
                // dan tugasnya adalah menghilangkan pulpy
                // 1
                temp = current.getNext();
                // @temp.prev masih berisi pulpy dan
                // harus dihilangkan
                // @current.prev berisi sedaap yang akan diisikan ke dalam @temp.prev
                temp.setPrev(current.getPrev());
                // @temp sekarang terisi : sedaap <- kopikap -> nabati

                // terakhir adalah membuat sedaap tidak memiliki relasi dengan pulpy
                // karena @current.prev masih indomie <- sedaap -> pulpy maka kita rubah
                // @current.prev.next
                current = current.getPrev();
                // @current akan diisi oleh @current.prev kemudian dirubah @current.next ke
                // @temp
                current.setNext(temp);
                // sehingga diperoleh data @current : indomie <- sedaap -> kopikap
                this.length--;
            }
            // ini untuk debugging
            return removed;
        }
        // ini untuk kembalian default
        return null;
    }
    // &insertAfter dibuat untuk return Node, agar debugging lebih mudah
    // parameter @data adalah target yang akan ditambahkan
    // parameter @target adalah target acuan

    // &printout dibuat untuk mencetak data dari @this.head.data
    public void printout() {
        // @current menyimpan @this.head untuk bermain pointer
        Node<T> current = this.head;
        // #while akan terus berjalan selama data @current tidak sama dengan null
        while (current != null) {
            // cetak data dari @current.data
            System.out.print(current.getData());
            if (current.getNext() != null)
                System.out.print(", ");
            // agar pointer @current tetap berjalan maju
            current = current.getNext();
        }
        // memberikan jarak di console
        System.out.println("");
    }

    // &printout dibuat untuk mencetak data dari @this.head.data dengab mundru
    public void printoutBack() {
        // @current menyimpan tail untuk bermain pointer
        Node<T> current = this.getTail();
        // #while akan terus berjalan selama data @current tidak sama dengan null
        while (current != null) {
            // cetak data dari @current.data
            System.out.println(current.getData());
            // agar pointer @current tetap berjalan mundur
            current = current.getPrev();
        }
    }

}
