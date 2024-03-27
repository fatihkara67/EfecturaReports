package Efectura.utilities;


import Efectura.pages.CCIPage;
import Efectura.pages.DiaPages;

public class Pages {
    private DiaPages diaPages;
    private CCIPage cciPage;



    public Pages() {
        this.diaPages = new DiaPages();
        this.cciPage = new CCIPage();
    }

    public DiaPages diaPages() {return diaPages;}

    public CCIPage cciPage() {return cciPage;}
}



