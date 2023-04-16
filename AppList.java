import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// List e ArrayList
public class AppS2 {

    public static void main(String args[]) {
        // para os testes foram executado um por vez

        executarThread("caso0050.txt", 50);
        executarThread("caso0100.txt", 100);
        executarThread("caso0200.txt", 200);
        executarThread("caso0400.txt", 400);
        executarThread("caso0600.txt", 600);
        executarThread("caso0800.txt", 800);
        executarThread("caso0900.txt", 900);
        executarThread("caso1000.txt", 1000);

    }

    private static void executarThread(String nomeArquivo, int jogo) {

        long tStart = System.currentTimeMillis();
        int rodadas = 0;
        ArrayList<Macaco> macacos = new ArrayList<>();

        try {
            List<String> lines = Files.lines(Paths.get(nomeArquivo)).collect(Collectors.toList());

            for (String line : lines) {
                String[] linha = line.split("\\s+");

                if (line.startsWith("Macaco")) {
                    int id = Integer.parseInt(linha[1]);
                    int envP = Integer.parseInt(linha[4]);
                    int envI = Integer.parseInt(linha[7]);
                    int tam = Integer.parseInt(linha[9]);
                    int nPar = 0;
                    int nImp = 0;

                    for (int i = 11; i < 11 + tam; i++) {
                        char ultimoValor = linha[i].charAt(linha[i].length() - 1);
                        int num = Character.getNumericValue(ultimoValor);
                        if (num % 2 == 0) {
                            nPar++;
                        } else {
                            nImp++;
                        }
                    }

                    macacos.add(new Macaco(id, envP, envI, nPar, nImp));
                } else {
                    rodadas = Integer.parseInt(linha[1]);
                }
            }

            rodadas = rodadas / 1000;// para o teste sem rodadas/1000, comente

            while (rodadas-- > 0) {
                for (Macaco macaco : macacos) {
                    macacos.get(macaco.getEnvP()).addPar(macaco.removePar());
                    macacos.get(macaco.getEnvI()).addImp(macaco.removeImp());
                }
            }

            Macaco vencedor = macacos.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor do jogo " + jogo + " foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis() - tStart) + " ms\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
