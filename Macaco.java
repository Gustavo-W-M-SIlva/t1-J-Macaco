

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

    public int getId(){ return id; }
    public int getEnvP(){ return envP; }
    public int getEnvI(){ return envI; }
    public int getTam(){ return nPar+nImp; }
    
    public int removePar(){ 
        this.rem = nPar;
        nPar = 0;
        return rem; 
    }
    public int removeImp(){ 
        this.rem = nImp;
        nImp = 0;
        return rem;  
    }

    public void addPar(int p){  nPar+= p; }
    public void addImp(int i){  nImp+= i; }
   

    @Override
    public String toString() { return "Macaco " + id + ",envP "+envP+" envI "+envI+ " nPar "+ nPar+" nImp "+ nImp+ " com " + getTam()+ " cocos;\n"; }
}
