package cz.czechitas.java2webapps.ukol3.controller;

import cz.czechitas.java2webapps.ukol3.entity.Vizitka;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler obsluhující zobrazování vizitek.
 */
@Controller
public class VizitkaController {
    private final List<Vizitka> seznamVizitek;

    public VizitkaController() {
        seznamVizitek = new ArrayList<>();
        seznamVizitek.add(
                new Vizitka(
                        "Dita (Přikrylová) Formánková",
                        "Czechitas z. s.",
                        "Václavské náměstí 837/11",
                        "11000 Praha 1",
                        "dita@czechitas.cs",
                        "+420 800123456",
                        "www.czechitas.cz"
                )
        );
        seznamVizitek.add(
                new Vizitka(
                        "Barbora Bühnová",
                        "Czechitas z. s.",
                        "Škrobárenská 511/3",
                        "61700 Brno",
                        null,
                        "+420 800123456",
                        "www.czechitas.cz"
                )
        );
        seznamVizitek.add(
                new Vizitka(
                        "Monika Ptáčníková",
                        "Czechitas z. s.",
                        "Technologická 372/2",
                        "70800 Ostrava-Pustkovec",
                        "monika@czechitas.cs",
                        "+420 800123456",
                        "www.czechitas.cz"
                )
        );
        seznamVizitek.add(
                new Vizitka(
                        "Mirka Zatloukalová",
                        "Czechitas z. s.",
                        "Vavrečkova 5262",
                        "76001 Zlín",
                        "mirka@czechitas.cs",
                        null,
                        "www.czechitas.cz"
                )
        );
        seznamVizitek.add(
                new Vizitka(
                        "Ondřej Čejka",
                        "Czechitas z. s.",
                        "Jungmannova 3",
                        "77900 Olomouc",
                        null,
                        null,
                        "www.czechitas.cz"
                )
        );
        seznamVizitek.add(
                new Vizitka(
                        "Kateřina Reiglová",
                        "Czechitas z. s.",
                        "Lipová 1789/9",
                        "37005 České Budějovice",
                        null,
                        null,
                        "www.czechitas.cz"
                )
        );
    }

    @GetMapping("/")
    public ModelAndView seznam() {
        ModelAndView result = new ModelAndView("seznam");
        result.addObject("seznam", seznamVizitek);
        return result;
    }

    @GetMapping(path = "/detail", params = "id")
    public ModelAndView detail(int id) {
        ModelAndView result = new ModelAndView("detail");
        result.addObject("vizitka", seznamVizitek.get(id));
        result.addObject("id", id);
        return result;
    }

    @GetMapping(path = "/nova")
    public ModelAndView novaVizitka() {
        ModelAndView result = new ModelAndView("novaVizitka");
        result.addObject("vizitky", seznamVizitek);
        return result;
    }

    // kdyz se dostane na hlavni stranku s parametry jmeno, firma, atd. => vytvori se v seznamu nova vizitka
    @PostMapping(value = "/", params = {"jmeno", "firma", "ulice", "obecPsc", "email", "telefon", "web"})
    public String pridejVizitku(Vizitka vizitka) {
        seznamVizitek.add(vizitka);
        return "redirect:/";
    }

    //kdyz se dostane na hlavni stranku s parametrem id => smaze se vizitka(id)
    @PostMapping(value = "/", params = "id")
    public String smazVizitku(int id) {
        seznamVizitek.remove(id);
        return "redirect:/"; // presmeruje na hlavni stranku bez parametru => zobrazi seznam vizitek
    }

}