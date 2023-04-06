

public class Macaco {
    
    private int id, envP, envI;
    private long nPar, nImp;
   
    public Macaco(int id, int envP, int envI, long nPar, long nImp){
        this.id = id;
        this.envP = envP;
        this.envI = envI;       
        this.nPar = nPar;       
        this.nImp = nImp;       
    }

    public int getId(){ return id; }
    public int getEnvP(){ return envP; }
    public int getEnvI(){ return envI; }
    public long getPar(){ return nPar; }
    public long getImp(){ return nImp; }
    public long getTam(){ return nPar+nImp; }
    public void clear(){ nPar = 0; nImp = 0; }

    public void addPar(long p){  nPar+= p; }
    public void addImp(long i){  nImp+= i; }
   

    @Override
    public String toString() { return "Macaco " + id + ",envP "+envP+" envI "+envI+ " nPar "+ nPar+" nImp "+ nImp+ " com " + getTam()+ " cocos;\n"; }
}
