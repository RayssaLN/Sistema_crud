import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Investimento> investimentos = new ArrayList<>();
    private static int nextId = 1;
    private static final String FILE_NAME = "Investimentos.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadInvestimentosFromFile();

        boolean exit = false;
        while (!exit) {
            System.out.println("MENU:");
            System.out.println("1. Criar Investimento");
            System.out.println("2. Ler Investimentos");
            System.out.println("3. Atualizar Investimento");
            System.out.println("4. Deletar Investimento");
            System.out.println("5. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createInvestimento();
                    break;
                case 2:
                    readInvestimentos();
                    break;
                case 3:
                    updateInvestimento();
                    break;
                case 4:
                    deleteInvestimento();
                    break;
                case 5:
                    exit = true;
                    saveInvestimentosToFile();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }

        scanner.close();
    }

    private static void createInvestimento() {
        System.out.print("Digite o nome do investimento: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o valor do investimento: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Clear the buffer
        System.out.print("Digite a data do investimento: ");
        String data = scanner.nextLine();

        Investimento investimento = new Investimento(nextId++, nome, valor, data);
        investimentos.add(investimento);
        saveInvestimentosToFile();

        System.out.println("Investimento criado com sucesso: " + investimento);
    }

    private static void readInvestimentos() {
        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento encontrado.");
        } else {
            System.out.println("Lista de Investimentos:");
            for (Investimento investimento : investimentos) {
                System.out.println(investimento);
            }
        }
    }

    private static void updateInvestimento() {
        System.out.print("Digite o ID do investimento a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Investimento investimento = findInvestimentoById(id);
        if (investimento == null) {
            System.out.println("Investimento não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome (deixe em branco para não alterar): ");
        String newNome = scanner.nextLine();
        System.out.print("Digite o novo valor (deixe em branco para não alterar): ");
        String valorStr = scanner.nextLine();
        System.out.print("Digite a nova data (deixe em branco para não alterar): ");
        String newData = scanner.nextLine();

        if (!newNome.isEmpty()) {
            investimento.setNome(newNome);
        }
        if (!valorStr.isEmpty()) {
            double newValor = Double.parseDouble(valorStr);
            investimento.setValor(newValor);
        }
        if (!newData.isEmpty()) {
            investimento.setData(newData);
        }

        saveInvestimentosToFile();
        System.out.println("Investimento atualizado com sucesso: " + investimento);
    }

    private static void deleteInvestimento() {
        System.out.print("Digite o ID do investimento a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Investimento investimento = findInvestimentoById(id);
        if (investimento == null) {
            System.out.println("Investimento não encontrado.");
            return;
        }

        investimentos.remove(investimento);
        saveInvestimentosToFile();
        System.out.println("Investimento deletado com sucesso.");
    }

    private static Investimento findInvestimentoById(int id) {
        for (Investimento investimento : investimentos) {
            if (investimento.getId() == id) {
                return investimento;
            }
        }
        return null;
    }

    private static void loadInvestimentosFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                double valor = Double.parseDouble(parts[2]);
                String data = parts[3];
                investimentos.add(new Investimento(id, nome, valor, data));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os investimentos: " + e.getMessage());
        }
    }

    private static void saveInvestimentosToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Investimento investimento : investimentos) {
                pw.println(investimento.getId() + "," + investimento.getNome() + "," + investimento.getValor() + "," + investimento.getData());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os investimentos: " + e.getMessage());
        }
    }
}