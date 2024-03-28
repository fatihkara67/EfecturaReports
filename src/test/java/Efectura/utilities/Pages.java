package Efectura.utilities;


import Efectura.pages.CCIPage;
import Efectura.pages.DiaPages;
import Efectura.pages.SlkPages;

public class Pages {
    private DiaPages diaPages;
    private CCIPage cciPage;
    private SlkPages slkPages;



    public Pages() {
        this.diaPages = new DiaPages();
        this.cciPage = new CCIPage();
        this.slkPages = new SlkPages();
    }

    public DiaPages diaPages() {return diaPages;}

    public CCIPage cciPage() {return cciPage;}

    public SlkPages slkPages() {return slkPages;}
}



