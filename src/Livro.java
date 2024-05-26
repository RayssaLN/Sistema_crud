class Livro {
    private int id;
    private String title;
    private String author;
    public Livro(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + title + ", Autor: " + author;
    }
}