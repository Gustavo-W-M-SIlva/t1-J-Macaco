
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

// Array e HashMap
public class ArrayHash {
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

    public static void executarThread(String nomeArquivo, int jogo) {
        //Para testar os casos de Array e do HashMap faça as devidas instruçoes de comentar e descomentar

        long tStart = System.currentTimeMillis();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo), Charset.defaultCharset())) {

            String line = reader.readLine();
            String[] linha = line.split("\\s+");

            // Padrao 1: Fazer (1) rodadas
            int rodadas = Integer.parseInt(linha[1]);

            //Macaco[] macacos = new Macaco[rodadas/100]; // descomente
             HashMap<Integer, Macaco> macacos = new HashMap<>(); // comente
            
            rodadas = rodadas/1000;// para o teste sem rodadas/1000, comente 

            while ((line = reader.readLine()) != null) {
                linha = line.split("\\s+");

                // Padrao 2: Macaco (1) par -> (4) impar -> (7) : (9) : (11 até n-1)
                int id = Integer.parseInt(linha[1]);
                int envP = Integer.parseInt(linha[4]);
                int envI = Integer.parseInt(linha[7]);
                int tam = Integer.parseInt(linha[9]);
                int nPar = 0;
                int nImp = 0;

                for (int i = 11; i < 11 + tam; i++) {
                    int num = Character.getNumericValue(linha[i].charAt(linha[i].length() - 1)); // descomente
                    // num = Integer.parseInt(linha[i]); // comente

                    if (num % 2 == 0) {
                        nPar++;
                    } else {
                        nImp++;
                    }
                }

                //macacos[id] = new Macaco(id, envP, envI, nPar, nImp); // descomente
                 macacos.put(id, new Macaco(id, envP, envI, nPar, nImp)); // comente

            }
            reader.close();

           /* // descomente
           while (rodadas-- > 0) { 
                for (Macaco macaco : macacos) {
                    macacos[macaco.getEnvP()].addPar(macaco.removePar());
                    macacos[macaco.getEnvI()].addImp(macaco.removeImp());
                }
            }

            // Ps. lambda tem seu charme
            Macaco vencedor = Arrays.stream(macacos).max((a, b) -> a.getTam() - b.getTam()).orElse(null); //comente
            */
             // comente
              while (rodadas-- > 0) {
              for (Macaco macaco : macacos.values()) {
              macacos.get(macaco.getEnvP()).addPar(macaco.removePar());
              macacos.get(macaco.getEnvI()).addImp(macaco.removeImp());
             }
              }
              
              // comente
              int maior = 0;
              int vencedor = 0;
              for (Integer id : macacos.keySet()) {
              Macaco v = macacos.get(id);
              if (v.getTam() > maior) {
              maior = v.getTam();
              vencedor = v.getId();
              
              }
              }
             

            

            System.out.println("\nNo jogo de caso " + jogo + " o vencedor foi o " + macacos.get(vencedor)); //comente
            //System.out.println("\nNo jogo de caso " + jogo + " o vencedor foi o " + vencedor); //descomente

            System.out.println("O tempo de excução do jogo foi de = " + (System.currentTimeMillis() - tStart) + " ms.\n");

        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
    }
}
