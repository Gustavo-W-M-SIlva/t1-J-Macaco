
public class Macaco {
    
    private int id, envP, envI;
    private int nPar, nImp, rem;
   
    public Macaco(int id, int envP, int envI, int nPar, int nImp){
        this.id = id;
        this.envP = envP;
        this.envI = envI;       
        this.nPar = nPar;       
        this.nImp = nImp;       
    }

    public int getId(){ return id; } // numero do macaco
    public int getEnvP(){ return envP; } // macaco que recebem os numeros pares
    public int getEnvI(){ return envI; } // macaco que recebem os numeros impares
    public int getTam(){ return nPar+nImp; } // soma a quantidade de impares e pares
    
    public int removePar(){  // retorna o numero de pares e reseta
        this.rem = nPar;
        nPar = 0;
        return rem; 
    }
    public int removeImp(){ // retorna o numero de impares e reseta
        this.rem = nImp;
        nImp = 0;
        return rem;  
    }

    public void addPar(int p){  nPar+= p; } // adiciona numero par
    public void addImp(int i){  nImp+= i; } // adiviona numero impar
   

    @Override
    public String toString() { return "Macaco " + id + ", com a quantidade de = " + getTam()+ " cocos."; }
}
