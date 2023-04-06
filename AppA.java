
    import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppA {

    public static void main(String[] args) throws Exception {
        Path path1 = Paths.get("caso"+1+"000.txt");
       /* for(int s = 0; s<11; s++ ){
        int s = 0;
            if (s == 0){
                 path1 = Paths.get("caso0050.txt");
            }
            else if(s<3)  path1 = Paths.get("caso0"+s+"00.txt");
            else if(s<9) {s+=1;  path1 = Paths.get("caso0"+s+"00.txt");}
            else if(s<10)   path1 = Paths.get("caso0"+s+"00.txt");
            else  path1 = Paths.get("caso"+1+"000.txt");
        */
        long tStart = System.currentTimeMillis();
        ArrayList<Macaco> listaMacaco = new ArrayList<>();
        int cont = 0,rodadas = 0, caracterN = 0 ; 
        ;
        BufferedReader reader;
        

        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;

            while ((line = reader.readLine()) != null) {
                int tam = 0, id = 0, envP = 0, envI = 0, nPar = 0, nImp = 0; 
                String data[] = line.split("");

                if (data[caracterN].startsWith("F")) {
                    caracterN += 6;
                    StringBuilder rodadaS = new StringBuilder();

                    while (caracterN < 11) {
                        rodadaS.append(data[caracterN]);
                        caracterN++;
                    }
                    rodadas = Integer.parseInt(rodadaS.toString());
                }

                if (data[caracterN].startsWith("M")) {
                    caracterN += 7;
                    String idS = data[caracterN];

                    while (data[caracterN + 1].equals(" ") == false) {
                        caracterN++;
                        idS = idS + data[caracterN];
                    }
                    id = Integer.parseInt(idS);
                }
                /*
                 * if (data[i].startsWith("M")) {
                 * i+=7;
                 * StringBuilder idS = new StringBuilder();
                 * while(data[i].equals(" ") == false){
                 * 
                 * idS.append(data[i]);
                 * i++;
                 * }
                 * id = Integer.parseInt(idS.toString());
                 * System.out.println(id);
                 * 
                 * }
                 */ // Nao esta funcionando, Pesquisar sobre
                 caracterN += 2;

                if (data[caracterN].startsWith("p")) {
                    caracterN += 7;
                    String envPS = data[caracterN];

                    while (!data[caracterN + 1].equals(" ")) {
                        caracterN++;
                        envPS = envPS + data[caracterN];
                    }
                    envP = Integer.parseInt(envPS);
                }
                caracterN += 2;

                if (data[caracterN].startsWith("i")) {
                    caracterN += 9;
                    String envIS = data[caracterN];

                    while (!data[caracterN + 1].equals(" ") ) {
                        caracterN++;
                        envIS = envIS + data[caracterN];
                    }
                    envI = Integer.parseInt(envIS);
                }
                caracterN += 2;                 

                if (data[caracterN].equals(":")) {
                    caracterN += 2;
                    StringBuilder tamS = new StringBuilder();

                    while (!data[caracterN].equals(" ")) {
                        tamS.append(data[caracterN]);
                        caracterN++;
                    }
                    tam = Integer.parseInt(tamS.toString());
                    caracterN += 4;
                    
                    int elementos = 0;
                    while (elementos != tam) {
                        boolean saida = true;

                        while (caracterN < data.length - 1 && saida) {
                            caracterN++;
                            if(data[caracterN].equals(" ")){
                                String auxS = data[caracterN-1];
                                saida = false;
                                if (Integer.parseInt(auxS) % 2 == 0)   
                                        nPar++;
                                else    nImp++;
                            }
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

            while (rodadas != 0) {
                rodadas--;

                for (Macaco v : listaMacaco) {
                    listaMacaco.get(v.getEnvP()).addPar(v.getPar());
                    listaMacaco.get(v.getEnvI()).addImp(v.getImp());
                    v.clear();
                }
            }

            long maior = 0; int vencedor = 0;
            for (Macaco v : listaMacaco) {
                if (v.getTam() > maior) {
                    maior = v.getTam();
                    vencedor = v.getId();
                }
            }

            System.out.println("O vencedor foi o " + listaMacaco.get(vencedor));
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis()-tStart) + " ms\n");
            

        } catch (IOException e) { System.err.format("Erro na leitura do arquivo: ", e);}
        }
    //}

}
