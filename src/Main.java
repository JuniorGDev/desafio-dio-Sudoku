import br.com.dio.models.Board;
import br.com.dio.models.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dio.utils.GameTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Board board;
    private static final int BOARD_TEMPLATE_SIZE = 9;

    public static void main(String[] args) {
        System.out.println("BEM VINDO AO SUDOKU");
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        s -> s.split(";")[1]
                ));
        var option = -1;
        while (true) {
            System.out.println("SELECIONE UMA OPÇÃO");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar o tabuleiro");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar o tabuleiro");
            System.out.println("7 - Finalizar o jogo");
            System.out.println("0 - Sair");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearBoard();
                case 7 -> finishGame();
                case 0 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void startGame(Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("O jogo ja foiiniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_TEMPLATE_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_TEMPLATE_SIZE; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                spaces.get(i).add(new Space(fixed, expected));
            }
        }
        board = new Board(spaces);
        System.out.println("O jogo está pronto para ser iniciado");
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        System.out.println("Digite a linha");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Digite a coluna");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Digite o número");
        var number = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, number)) {
            System.out.printf("A posição (%s,%s) ja foi preenchida com um numero fixo\n", col, row);
        }
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        System.out.println("Digite a linha");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Digite a coluna");
        var col = runUntilGetValidNumber(0, 8);
        if (!board.clearSpace(col, row)) {
            System.out.printf("A posição (%s,%s) ja foi preenchida com um numero fixo\n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_TEMPLATE_SIZE; i++) {
            for (var col: board.getSpaces()) {
                args[argPos ++] = " " + (isNull(col.get(i).getActual()) ? " " : col.get(i).getActual());
            }
        }
        System.out.println("Seu jogo atual:");
        System.out.printf((BOARD_TEMPLATE) + "%n", args);
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        System.out.println("O jogo atual esta em: " + board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo possui erros");
        } else {
            System.out.println("O jogo nao possui erros");
        }
    }

    private static void clearBoard() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        System.out.println("Tem certeza que deseja limpar o tabuleiro?");
        System.out.println("1 - Sim");
        System.out.println("2 - Nao");
        var confirm = scanner.nextInt();
        while (confirm != 1 && confirm != 2) {
            System.out.println("Digite 1 ou 2");
            confirm = scanner.nextInt();
        }
        if (confirm == 1) {
            board.reset();
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("O jogo foi concluido");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("O jogo possui erros");
        } else {
            System.out.println("O jogo nao foi concluido");
        }
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        var current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.printf("Digite um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}