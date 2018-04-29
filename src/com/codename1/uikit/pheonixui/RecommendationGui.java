/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Recommendation;
import com.bonplan.services.RecommendationService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import javafx.scene.control.Tab;


/**
 *
 * @author amira
 */
public class RecommendationGui extends BaseForm {

    Resources theme;

    public RecommendationGui() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public RecommendationGui(Resources res) throws IOException {
        //super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Recommendation", "Title")
                )
        );
        super.installSidemenu(res);
        // getContentPane().setScrollable(false);
        RecommendationService rs = new RecommendationService();
        // rs.getallrec().forEach();
        Label l=new Label("Restaurant");
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ComboBox<String> combo = new ComboBox<>("Resaurant", "Produit", "Prestation", "Evenement", "Voyage");
        C0.add(combo);
        Tabs t = new Tabs();
        t.addTab("Tab2", new SpanLabel("Some text directly in the tab"));
        t.addTab("Tab2", new SpanLabel("Some text directly in the tab"));
        C0.add(t);
        ArrayList<Recommendation> rec = rs.getallrec();
        for (Recommendation r : rec) {

            C0.add(addItem(r));

        }
        
        
        add(C0);

    }

    public Container addItem(Recommendation r) throws IOException {
        //
        Container C1 = new Container(new BorderLayout());
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer img = new ImageViewer();
//image=Image.createImage("/tunis.jpg").fill(170, 100);
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        img.setImage(URLImage.createToStorage(enc, r.getPhoto(), "http://localhost/PIDEV/web/uploads/" + r.getPhoto()).scaled(60, 60));

        Label nom = new Label(r.titre);
        //nom.setUIID("SlightlySmallerFontLabelLeft");
        Label desc = new Label(r.description);
        desc.setUIID("SlightlySmallerFontLabelLeft");
        Button btn = new Button("detail");
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

        System.out.println(r.titre);
        System.out.println(r.description);
        C3.add(nom);
        C3.add(desc);
        C3.add(starRank);
        C1.add(BorderLayout.WEST, img);
        C1.add(BorderLayout.CENTER, C3);
        C1.add(BorderLayout.EAST, btn);
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
