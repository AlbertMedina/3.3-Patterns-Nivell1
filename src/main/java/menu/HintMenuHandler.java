package menu;

import hint.Hint;
import hint.HintService;

public class HintMenuHandler extends EntityMenuHandler<Hint> {

    HintService hintService;

    public HintMenuHandler(Hint hint) {
        super(hint);
        hintService = new HintService();
    }

    @Override
    protected int showMenuAndReadOption() {
        return 0;
    }

    @Override
    protected void handleOption(int option) {

    }
}