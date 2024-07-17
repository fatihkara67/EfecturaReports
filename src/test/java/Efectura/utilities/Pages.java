package Efectura.utilities;


import Efectura.pages.DiaPages;
import Efectura.pages.MailPage;
import Efectura.pages.SlkPages;

public class Pages {
    private DiaPages diaPages;
    private SlkPages slkPages;
    private MailPage mailPage;



    public Pages() {
        this.diaPages = new DiaPages();
        this.slkPages = new SlkPages();
        this.mailPage = new MailPage();
    }

    public DiaPages diaPages() {return diaPages;}

    public SlkPages slkPages() {return slkPages;}

    public MailPage mailPage() {return mailPage;}

}



