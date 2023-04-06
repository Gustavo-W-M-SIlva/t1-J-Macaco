
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        Path path1 = Paths.get("caso1000.txt");
        long tStart = System.currentTimeMillis();
        ArrayList<Macaco> listaMacaco = new ArrayList<>();
        int cont = 0,rodadas = 0, caracterN = 0 ; 
        BufferedReader reader;

        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;

            while ((line = reader.readLine()) != null) {
                int tam = 0, id = 0, envP = 0, envI = 0, nPar = 0, nImp = 0; 
                String[] data = line.split("\\s+");

                if (data[0].startsWith("F")) {
                    caracterN += 6;
                    StringBuilder rodadaS = new StringBuilder();

                    while (caracterN < 11) {
                        rodadaS.append(data[caracterN]);
                        caracterN++;
                    }
                    rodadas = Integer.parseInt(rodadaS.toString());
                }

                if (data[0].startsWith("M")) {
                    String idS = data[1];                    
                    id = Integer.parseInt(idS);
                }
                
                 caracterN += 2;

                if (data[2].startsWith("p")) {
                    String envPS = data[4];

                    
                    envP = Integer.parseInt(envPS);
                }
                caracterN += 2;

                if (data[5].startsWith("i")) {
                    caracterN += 9;
                    String envIS = data[7];

                    envI = Integer.parseInt(envIS);
                }
                caracterN += 2;                 

                if (data[8].equals(":")) {
                    caracterN += 2;
                   
                    tam = Integer.parseInt(data[9]);
                    caracterN += 4;
                    
                    int elementos = 0;
                    while (elementos != tam) {
                        boolean saida = true;

                        for(int i = 11; i < data.length - 1; i++) {
                            caracterN++;
                            
                                String auxS = data[i];
                                saida = false;
                                if (Integer.parseInt(auxS) % 2 == 0)   
                                        nPar++;
                                else    nImp++;
                            }
                        
                        elementos++;
                        caracterN += 2;
                      
                    }
                }

                if (cont > 0) {
                    Macaco macaco = new Macaco(id, envP, envI, nPar, nImp);
                    listaMacaco.add(macaco);
                }
                caracterN = 0;
                cont++;
            }          

            while (rodadas-- > 0) {
                for (Macaco v : listaMacaco) {
                    listaMacaco.get(v.getEnvP()).addPar(v.getPar());
                    listaMacaco.get(v.getEnvI()).addImp(v.getImp());
                    v.clearPI();
                }
            }

            Macaco vencedor = listaMacaco.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis()-tStart) + " ms\n");
            

        } catch (IOException e) { System.err.format("Erro na leitura do arquivo: ", e);}
        }
   

}
