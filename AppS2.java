import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class AppS2 {

    public static void main(String args[]) {
        Path  path = Paths.get("caso"+1+"000.txt");
       /* for(int s = 0; s<11; s++ ){
            if (s == 0){
                 path = Paths.get("caso0050.txt");
            }
            else if(s<3)  path = Paths.get("caso0"+s+"00.txt");
            else if(s<9) {s+=1;  path = Paths.get("caso0"+s+"00.txt");}
            else if(s<10)   path = Paths.get("caso0"+s+"00.txt");
            else 
        */
        long tStart = System.currentTimeMillis();
        int rodadas = 0;
        ArrayList<Macaco> macacos = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toArray(String[]::new)) {
                String[] data = line.split("\\s+");

                if (line.startsWith("F")) {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        rodadas = Integer.parseInt(matcher.group());
                    }
                } else if (line.startsWith("M")) {
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
            }

            while (rodadas-- > 0) {
                for (Macaco macaco : macacos) {
                    macacos.get(macaco.getEnvP()).addPar(macaco.getPar());
                    macacos.get(macaco.getEnvI()).addImp(macaco.getImp());
                    macaco.clear();
                }
            }
           
            Macaco vencedor = macacos.stream().max((a, b) -> a.getTam() - b.getTam()).orElse(null);

            System.out.println("O vencedor foi o " + vencedor);
            System.out.println("Tempo de Execução com operador = " + (System.currentTimeMillis() - tStart) + " ms\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }}
    }



