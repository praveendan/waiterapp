package com.example.loginapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenuContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MenuItem> ITEMS = new ArrayList<MenuItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

    private static final int COUNT = 24;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            boolean isCatStart =  false;
            String category;
            if(i % 6 == 1){
                isCatStart = true;
                category = "cat" + i;
            }else{
                category = "";
            }
            addItem(createDummyItem(i, isCatStart, category));
        }
    }

    private static void addItem(MenuItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MenuItem createDummyItem(int position, boolean isCatStart, String category) {
        return new MenuItem(String.valueOf(position), "Item " + position, makeDetails(position), isCatStart, category);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MenuItem {
        public boolean isFirst = false;
        public final String id;
        public final String content;
        public final String details;
        public final String category;

        public MenuItem(String id, String content, String details, boolean isCatStart, String category) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.isFirst = isCatStart;
            this.category = category;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
