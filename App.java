
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class App {

    public static void main(String[] args)  {
        Path path1 = Paths.get("caso1000.txt");
        
        long tStart = System.currentTimeMillis();
        List<Macaco> macacos = new ArrayList<>(); 

        try(BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            
            String line = reader.readLine() ;
            String[] data = line.split("\\s+");
            int rodadas = Integer.parseInt(data[1]);

            while ((line = reader.readLine()) != null) {
            data = line.split("\\s+");
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
            while (rodadas-- > 0) {
                for (Macaco macaco : macacos) {
                    macacos.get(macaco.getEnvP()).addPar(macaco.removePar());
                    macacos.get(macaco.getEnvI()).addImp(macaco.removeImp());
                }

            }

            Macaco vencedor = macacos.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis()-tStart) + " ms\n");
            

        } catch (IOException e) { System.err.format("Erro na leitura do arquivo: ", e);}
        }
   

}
