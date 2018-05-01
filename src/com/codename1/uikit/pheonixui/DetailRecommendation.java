/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.RecommendationService;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
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

    public class DetailRecommendation extends BaseForm {

    Resources theme;

    public DetailRecommendation() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public DetailRecommendation(Resources res) throws IOException {
        //super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Recommendation", "Title")
                )
        );
        super.installSidemenu(res);
        // getContentPane().setScrollable(false);
        ImageViewer img = new ImageViewer();
//image=Image.createImage("/tunis.jpg").fill(170, 100);
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        Recommendation r=Recommendation.recommendation;
        img.setImage(URLImage.createToStorage(enc, r.getPhoto(), "http://localhost/PIDEV/web/uploads/" + r.getPhoto()).scaled(100, 100));
        Label l1=new Label(r.titre);
        Label lc=new Label("Categorie : ");
        Label ln=new Label("Nom : ");
        Label la=new Label("Adresse : ");
        Label lnt=new Label("Numero de telephone : ");
        Label le=new Label("Adresse Email : ");
        Label lnote=new Label("Note : ");
        Label l2=new Label(r.categorie);
        l2.setUIID("SlightlySmallerFontLabelLeft");
        Label l3=new Label(r.nom);
        Label l4=new Label(r.adresse);
        Label l5=new Label(r.num_tel);
        Label l6=new Label(r.email);
        l3.setUIID("SlightlySmallerFontLabelLeft");
        l4.setUIID("SlightlySmallerFontLabelLeft");
        l5.setUIID("SlightlySmallerFontLabelLeft");
        l6.setUIID("SlightlySmallerFontLabelLeft");
        Container C0=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C5=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C6=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button btns=new Button("supprimer");
        Button btnc=new Button("Afficher les commentaire");
        C.add(l1);
        C.add(img);
        C0.add(lc);
        C0.add(l2);
        C1.add(ln);
        C1.add(l3);
        C2.add(la);
        C2.add(l4);
        C3.add(lnt);
        C3.add(l5);
        C4.add(le);
        C4.add(l6);
        C.add(C0);
        C.add(C1);
        C.add(C2);
        C.add(C3);
        C.add(C4);
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
        C5.add(lnote);
        C5.add(starRank);
        C.add(C5);
        if(r.id_owner==User.getUserconnected().getId())
            C6.add(btns);
        C6.add(btnc);
        C.add(C6);
        btnc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    new AfficheCommentaires(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            }
        });
        btns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RecommendationService rs=new RecommendationService();
                rs.supprec(r.id);
                C1.removeAll();
                C1.getParent().removeComponent(C1);
                try {
                    new RecommendationGui(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            }
        });
        add(C);
        getToolbar().addCommandToRightBar("Back", theme.getImage("back-command.png"), b->{
            try {
                new RecommendationGui(res).showBack();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());            }
});
        
        

    }

    

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
}
