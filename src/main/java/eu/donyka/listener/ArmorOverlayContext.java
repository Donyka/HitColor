package eu.donyka.listener;

public final class ArmorOverlayContext {
    private static final ThreadLocal<Integer> OVERLAY = new ThreadLocal<>();

    private ArmorOverlayContext() {
    }

    public static void set(int overlay) {
        OVERLAY.set(overlay);
    }

    public static int apply(int originalOverlay) {
        Integer overlay = OVERLAY.get();
        return overlay != null ? overlay : originalOverlay;
    }

    public static boolean hasOverlay() {
        return OVERLAY.get() != null;
    }

    public static void clear() {
        OVERLAY.remove();
    }
}

