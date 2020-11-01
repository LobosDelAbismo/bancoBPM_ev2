package Clases;

public class Creditos
{
    private int cHipotecario;
    private int cuotaCH;
    private int cAutomotriz;
    private int cuotaCA;

    public Creditos()
    {
        cHipotecario = 1000000;
        cuotaCH = 12;
        cAutomotriz = 500000;
        cuotaCA = 8;
    }

    public int getcHipotecario(){ return cHipotecario; }

    public int getcuotaCH(){ return cuotaCH; }

    public int getcAutomotriz() { return cAutomotriz; }

    public int getcuotaCA() { return cuotaCA; }
}
