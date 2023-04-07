import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class AppS2 {

    public static void main(String args[]) {
       
       /*  Estava tentando ajudar meu amigos, que ja estão em alguns semestres a frente, e vi que eles 
        estavam fazendo uso de um "tal de Thread"
        perguntei o que era e achei muito interesante o conceito, como ja havia implementado o meu 
        codigo original, logo percebi que ele se encaixava perfeitamente para melhorar o meu codigo. Entao resolvi ir atras de
        mais informacoes para implementa-lo. 
        https://www.devmedia.com.br/trabalhando-com-threads-em-java/28780
        Eu analisei esse codigo e percebi que com uma pequena modificação ele melhoraria muito, ao inves de fazer varios new Thread
        como esse usuario, resolvi criar o metodo executarThread, para melhor aproveitamento.

        
        executarThread("caso0050.txt", 50);
       executarThread("caso0100.txt",100);
       executarThread("caso0200.txt",200);
       executarThread("caso0400.txt",400);
       executarThread("caso0600.txt",600);
       executarThread("caso0800.txt",800);
       executarThread("caso0900.txt",900);*/
       executarThread("caso1000.txt",1000);
            
        
    }

    private static void executarThread(String nomeArquivo, int jogo) {
       //new Thread(() -> { 
        //https://dicasdejava.com.br/java-como-criar-uma-thread-com-lambda/
        //Nas minhas pesquisas sobre o uso do Thread encontrei essa maneira mais simples visualmente de implementa-la
        Path path = Paths.get(nomeArquivo);
        long tStart = System.currentTimeMillis();
        int rodadas = 0;
        ArrayList<Macaco> macacos = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path).parallel()) {
            for (String line1 : lines.toArray(String[]::new)) {
                String[] data = line1.split("\\s+");
        /*
         Nao estava familiarizado com o uso do split 
         https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
        */
       
               if (line1.startsWith("M")) {
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
                } else  {
                    rodadas = Integer.parseInt(data[1]);
                } 
            }

            while (rodadas-- > 0) {
                for (Macaco macaco : macacos) {
                    macacos.get(macaco.getEnvP()).addPar(macaco.removePar());
                    macacos.get(macaco.getEnvI()).addImp(macaco.removeImp());
                }
            }
           
            Macaco vencedor = macacos.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor do jogo "+ jogo +" foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis() - tStart) + " ms\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }//https://www.baeldung.com/java-when-to-use-parallel-stream
    }//).start();
}
//}



