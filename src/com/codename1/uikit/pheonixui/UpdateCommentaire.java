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
import com.bonplan.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
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
public class UpdateCommentaire extends BaseForm {

    Resources theme;

    public UpdateCommentaire() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public UpdateCommentaire(Resources res) throws IOException {
        //super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Recommendation", "Title")
                )
        );
        super.installSidemenu(res);
        // getContentPane().setScrollable(false);
        
        Container C9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextArea ta = new TextArea();
        ta.setText(Commentaire.commentaire.getContenu());
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
        starRank.setProgress((int) Commentaire.commentaire.getNote());
        Button b = new Button("Modifier");
        C9.add(ta);
        C9.add(starRank);
        C9.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Commentaire c= Commentaire.commentaire;
                c.setContenu(ta.getText());
                c.setNote(starRank.getProgress());
                new CommentaireService().UpdateCommentaire(c);
                try {
                    new AfficheCommentaires(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(C9);
        getToolbar().addCommandToRightBar("Back", theme.getImage("back-command.png"), f -> {
            try {
                new AfficheCommentaires(res).showBack();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
}
