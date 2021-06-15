/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.ScaledImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FlatTitlePaneIcon
extends ScaledImageIcon {
    private final List<Image> images;

    public static Icon create(List<Image> list, Dimension dimension) {
        ArrayList<Image> arrayList = new ArrayList<Image>();
        for (Image image3 : list) {
            if (MultiResolutionImageSupport.isMultiResolutionImage(image3)) {
                arrayList.addAll(MultiResolutionImageSupport.getResolutionVariants(image3));
                continue;
            }
            arrayList.add(image3);
        }
        arrayList.sort((image, image2) -> image.getWidth(null) - image2.getWidth(null));
        return new FlatTitlePaneIcon(arrayList, dimension);
    }

    private FlatTitlePaneIcon(List<Image> list, Dimension dimension) {
        super(new ImageIcon(list.get((int)1292223316L ^ 0x4D05C354)), dimension.width, dimension.height);
        this.images = list;
    }

    @Override
    protected Image getResolutionVariant(int n2, int n3) {
        for (Image image : this.images) {
            if (n2 > image.getWidth(null) || n3 > image.getHeight(null)) continue;
            return image;
        }
        return this.images.get(this.images.size() - (int)((long)-1754665985 ^ (long)-1754665986));
    }
}

