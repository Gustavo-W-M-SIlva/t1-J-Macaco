
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class AppDivisao {
    public static void main(String args[]) {

        /*
         * Estava tentando ajudar meu amigos, que ja estão em alguns semestres a frente,
         * e vi que eles
         * estavam fazendo uso de um "tal de Thread"
         * perguntei o que era e achei muito interesante o conceito, como ja havia
         * implementado o meu
         * codigo original, logo percebi que ele se encaixava perfeitamente para
         * melhorar o meu codigo. Entao resolvi ir atras de
         * mais informacoes para implementa-lo.
         * https://www.devmedia.com.br/trabalhando-com-threads-em-java/28780
         * Eu analisei esse codigo e percebi que com uma pequena modificação ele
         * melhoraria muito, ao inves de fazer varios new Thread
         * como esse usuario, resolvi criar o metodo executarThread, para melhor
         * aproveitamento.
         ***/

        //executarThread("caso0050.txt", 50);
        //executarThread("caso0100.txt", 100);
        //executarThread("caso0200.txt", 200);
        executarThread("caso0400.txt", 400);
        //executarThread("caso0600.txt", 600);
        //executarThread("caso0800.txt", 800);
        //executarThread("caso0900.txt", 900);
        //executarThread("caso1000.txt", 1000);

    }

    public static void executarThread(String nomeArquivo, int jogo) {
        //new Thread(() -> {

            long tStart = System.currentTimeMillis();
            //List<Macaco> macacos = new ArrayList<>();

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo), Charset.defaultCharset())) {

                String line = reader.readLine();
                String[] data = line.split("\\s+");// separa a linha por String
                // como sabemos com exatidão o padrao do arquivo podemos buscar o valor
                // exatamente onde queremos
                // Na primeira linha temos o (Padrao 1) numero de rodadas, e nas proximas
                // (Padrao 2) temos as
                // informaçoes de cada macaco: o seu numero,
                // para quem enviar os numeros par e os impares, e o numero de cocos
                // Padrao 1: Fazer (1) rodadas
                // Padrao 2: Macaco (1) par -> (4) impar -> (7) : (9) : (11 até n-1)
                int rodadas = Integer.parseInt(data[1])/100;
                Macaco [] macacos = new Macaco[rodadas];
                while ((line = reader.readLine()) != null) {
                    data = line.split("\\s+");

                    // Padrao 2: Macaco (1) par -> (4) impar -> (7) : (9) : (11 até n-1)
                    int id = Integer.parseInt(data[1]); 
                    int envP = Integer.parseInt(data[4]); 
                    int envI = Integer.parseInt(data[7]); 
                    int tam = Integer.parseInt(data[9]); 
                    int nPar = 0; 
                    int nImp = 0;

                    for (int i = 11; i < 11 + tam; i++) { // percorre os cocos para verificar a quantidade de pares e
                                                          // impares, pegando apenas o ultimo caracter do numero
                        //int num = Character.getNumericValue(data[i].charAt(data[i].length() - 1));
                        //Integer.parseInt(str.substring(str.length() - 1));
                        if (Character.getNumericValue(data[i].charAt(data[i].length() - 1)) % 2 == 0) {
                            nPar++;
                        } else {
                            nImp++;
                        }
                    }

                    macacos[id]=new Macaco(id, envP, envI, nPar, nImp); // adiciona o macaco à lista

                }
                reader.close();

                //for (Macaco macaco : macacos) { // percorre a lista de jogadores, e envia o cocos pares e impares
                    // para
                    // seus corespondentes
                   //     macacos[macaco.getEnvP()].addPar(macaco.removePar());
                   //     macacos[macaco.getEnvI()].addImp(macaco.removeImp());
                   // }
                   // rodadas--;
                rodadas = 41;
                while (rodadas-- > 0) {// executa o numero de rodadas
                    for (Macaco macaco : macacos) { // percorre a lista de jogadores, e envia o cocos pares e impares
                                                    // para
                                                    // seus corespondentes
                        macacos[macaco.getEnvP()].addPar(macaco.removePar());
                        macacos[macaco.getEnvI()].addImp(macaco.removeImp());
                    }
                 }

                // verificamos qual macaco teve o maior numero de cocos
                // Ps. lambda tem seu charme
                /*int maior=0;int vencedor=0;
                for (Macaco id : macacos) {
                    
                    if (id.getTam() > maior) {
                        maior = id.getTam();
                        vencedor = id.getId();
                    }
                }
*/
                // Ps. lambda tem seu charme
                Macaco vencedor = Arrays.stream(macacos).max((a, b) -> a.getTam() - b.getTam()).orElse(null);


                System.out.println("\nNo jogo de caso "+jogo+" o vencedor foi o " + vencedor);
                System.out.println(
                        "O tempo de excução do jogo foi de = " + (System.currentTimeMillis() - tStart) + " ms.\n");

            } catch (IOException e) {
                System.err.format("Erro na leitura do arquivo: ", e);
            }
        }//).start();
    }
//}
