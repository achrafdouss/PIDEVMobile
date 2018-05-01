/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.CommentaireService;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Achraf
 */
public class AfficheCommentaires extends BaseForm {

    Resources theme;

    public AfficheCommentaires() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AfficheCommentaires(Resources res) throws IOException {
        //super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Recommendation", "Title")
                )
        );
        super.installSidemenu(res);
        // getContentPane().setScrollable(false);
        CommentaireService cs = new CommentaireService();
        // rs.getallrec().forEach();
        Label l = new Label("Restaurant");
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C10 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //ComboBox<String> combo = new ComboBox<>("Resaurant", "Produit", "Prestation", "Evenement", "Voyage");
        //C0.add(combo);

        ArrayList<Commentaire> comm = cs.getallrec(Recommendation.recommendation.id);
        for (Commentaire c : comm) {

            C10.add(addItem(c));

        }
        add(C10);
        Container C9=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextArea ta=new TextArea();
         Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);

        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        System.out.println(fullStar.getWidth());

        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        starRank.setEditable(true);
        Button b=new Button("Ajouter");
        C9.add(ta);
        C9.add(starRank);
        C9.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Commentaire c=new Commentaire(User.getUserconnected().getId(), Recommendation.recommendation.id, ta.getText(), starRank.getProgress());
                new CommentaireService().ajoutCommentaire(c);
                try {
                    new AfficheCommentaires(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            }
        });
        getToolbar().addCommandToRightBar("Back", theme.getImage("back-command.png"), f->{
        try {
                new DetailRecommendation(res).showBack();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());            }
});
        
        add(C9);
        
        
        
    }

    public Container addItem(Commentaire r) throws IOException {
        //
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
//image=Image.createImage("/tunis.jpg").fill(170, 100);
       User u=new UserService().getuserId(r.id_owner);
        Label nom = new Label(u.getNom());
        //nom.setUIID("SlightlySmallerFontLabelLeft");
        Label contenu = new Label(r.contenu);
        contenu.setUIID("SlightlySmallerFontLabelLeft");
        Button btn = new Button("detail");
       
        Button btns = new Button("Supprimer");
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);

        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        System.out.println(fullStar.getWidth());

        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        starRank.setEditable(false);
        starRank.setProgress((int) r.note);

        
        C3.add(nom);
        C3.add(contenu);
        C3.add(starRank);
        
        C1.add(C3);
       // C2.add(btn);
         System.out.println("++++++++++++++++++++"+r.id_owner);
         System.out.println("++++++++++++++++++++"+User.getUserconnected().getId());
        
        nom.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  Recommendation.recommendation=r;
                try {
                    new DetailRecommendation(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            }
        });
        
        
        
        C1.add(C2);
        // C0.add(C1);

        return C1;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
}
