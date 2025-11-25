package menu;

import decoration.Decoration;
import decoration.DecorationService;

public class DecorationMenuHandler extends EntityMenuHandler<Decoration> {

    DecorationService decorationService;

    public DecorationMenuHandler(Decoration decoration) {
        super(decoration);
        decorationService = new DecorationService();
    }

    @Override
    protected int showMenuAndReadOption() {
        return 0;
    }

    @Override
    protected void handleOption(int option) {

    }
}
