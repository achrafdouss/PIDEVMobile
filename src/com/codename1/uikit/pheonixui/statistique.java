/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Prod;
import com.bonplan.services.StatProduit;
import com.bonplan.entities.Produit;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 
 */
public class statistique extends BaseForm{

    public Form f;
    Resources theme ;
      statistique(Resources res) {
  theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Statistique Produit", "Title")
                )
        );    }
     
      public int stock;
    public int nb = 20;
    StatProduit ser = new StatProduit();
    List<Prod> list = ser.getList2();
    public int j = list.size();

    public int[] tab = new int[j];

  

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, int[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (int value : values) {
            series.add("Stock " + ++k, value);
        }

        
        return series;
    } 
    public Form createPieChartForm() {
        // Generate the values

        int[] values = new int[]{12, 14, 11, 10, 19};

        for (Prod p : list) {
            //System.out.println(p.getNbr());
            tab[stock] = p.getStock();
            stock++;

        }

        for (int t : tab) {
            //System.out.println(p.getNbr());
            System.err.println(t);

        }

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.BLUE};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.YELLOW);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("yosra", tab), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
         f = new Form("N°:Stock/Catégorie", new BorderLayout());
      
        f.add(BorderLayout.CENTER, c);
        //f.show();
        f.getToolbar().addCommandToRightBar("Home", null, (evt) -> {new HomeProduit(theme).showBack();
                   }) ;

        return f;
    }

    public Form getF() {
        return f;
    }
    
    
    
    

}

