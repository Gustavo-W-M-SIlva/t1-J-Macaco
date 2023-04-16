import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class AppS1 {

    public static void main(String args[]) {
          executarThread("caso0050.txt", 50);
          //executarThread("caso0100.txt",100);
         // executarThread("caso0200.txt",200);
          //executarThread("caso0400.txt",400);
          //executarThread("caso0600.txt",600);
         // executarThread("caso0800.txt",800);
         // executarThread("caso0900.txt",900);
         
      //  executarThread("caso1000.txt", 1000);

    }

    private static void executarThread(String nomeArquivo, int jogo) {
        new Thread(() -> {
        // https://dicasdejava.com.br/java-como-criar-uma-thread-com-lambda/
        // Nas minhas pesquisas sobre o uso do Thread encontrei essa maneira mais
        // simples visualmente de implementa-la
        long tStart = System.currentTimeMillis();
        int rodadas = 500;
        ArrayList<Macaco> macacos = new ArrayList<>();

        try  {
            Stream<String> lines = Files.lines(Paths.get(nomeArquivo));
            Optional<String> primeiraLinha = lines.findFirst();
    if (primeiraLinha.isPresent()) {
    String linha = primeiraLinha.get();
    System.out.println(linha);
} 
            //rodadas = Integer.parseInt(data1[1]);
            
            lines.forEach(line -> {
                String[] data = line.split("\\s+");
                /*
                 * Nao estava familiarizado com o uso do split
                 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
                 */
                System.out.println(line);

                if (line.startsWith("Macaco")) {
                    int id = Integer.parseInt(data[1]);
                    int envP = Integer.parseInt(data[4]);
                    int envI = Integer.parseInt(data[7]);
                    int tam = Integer.parseInt(data[9]);
                    int nPar = 0;
                    int nImp = 0;

                    for (int i = 11; i < 11 + tam; i++) {
                        char ultimoValor = data[i].charAt(data[i].length() - 1);
                        int num = Character.getNumericValue(ultimoValor);
                        if (num % 2 == 0) {
                            nPar++;
                        } else {
                            nImp++;
                        }
                    }

                    macacos.add(new Macaco(id, envP, envI, nPar, nImp));
                } 
            });

            lines.close();

            while (rodadas-- > 0) {
                for (Macaco macaco : macacos) {
                    macacos.get(macaco.getEnvP()).addPar(macaco.removePar());
                    macacos.get(macaco.getEnvI()).addImp(macaco.removeImp());
                }
            }

            Macaco vencedor = macacos.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor do jogo " + jogo + " foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis() - tStart) + " ms\n");
            System.out.println();

        } catch (IOException ex) {
            ex.printStackTrace();
        } // https://www.baeldung.com/java-when-to-use-parallel-stream
    } ).start();
}
 }
