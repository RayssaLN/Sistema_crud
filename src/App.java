import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static int nextId = 1;
    private static final String FILE_NAME = "livros.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadLivrosFromFile();

        boolean exit = false;
        while (!exit) {
            // Apresenta o menu ao usuário
            System.out.println("Menu:");
            System.out.println("1. Criar Livro");
            System.out.println("2. Ler Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("5. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createLivro();
                    break;
                case 2:
                    readLivros();
                    break;
                case 3:
                    updateLivro();
                    break;
                case 4:
                    deleteLivro();
                    break;
                case 5:
                    exit = true;
                    saveLivrosToFile();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }

        scanner.close();
    }

    private static void createLivro() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();

        Livro livro = new Livro(nextId++, title, author);
        livros.add(livro);
        saveLivrosToFile();

        System.out.println("Livro criado com sucesso: " + livro);
    }

    private static void readLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Lista de livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void updateLivro() {
        System.out.print("Digite o ID do livro a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Livro livro = findLivroById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.print("Digite o novo título (deixe em branco para não alterar): ");
        String newTitle = scanner.nextLine();
        System.out.print("Digite o novo autor (deixe em branco para não alterar): ");
        String newAuthor = scanner.nextLine();

        if (!newTitle.isEmpty()) {
            livro.setTitle(newTitle);
        }
        if (!newAuthor.isEmpty()) {
            livro.setAuthor(newAuthor);
        }

        saveLivrosToFile();
        System.out.println("Livro atualizado com sucesso: " + livro);
    }

    private static void deleteLivro() {
        System.out.print("Digite o ID do livro a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Livro livro = findLivroById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livros.remove(livro);
        saveLivrosToFile();
        System.out.println("Livro deletado com sucesso.");
    }

    private static Livro findLivroById(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    private static void loadLivrosFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                livros.add(new Livro(id, title, author));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }
    }

    private static void saveLivrosToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Livro livro : livros) {
                pw.println(livro.getId() + "," + livro.getTitle() + "," + livro.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }
}