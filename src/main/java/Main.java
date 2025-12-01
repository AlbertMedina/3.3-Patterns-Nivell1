import Inventory.InventoryService;
import certification.CertificationService;
import decoration.DecorationService;
import escapeRoom.EscapeRoomService;
import hint.HintService;
import menu.MainMenuHandler;
import reward.RewardService;
import room.RoomService;
import ticket.TicketService;
import user.UserService;

public class Main {
    public static void main(String[] args) {

        EscapeRoomService escapeRoomService = new EscapeRoomService();
        UserService userService = new UserService();
        InventoryService inventoryService = new InventoryService();
        RoomService roomService = new RoomService();
        HintService hintService = new HintService();
        DecorationService decorationService = new DecorationService();
        TicketService ticketService = new TicketService();
        RewardService rewardService = new RewardService();
        CertificationService certificationService = new CertificationService();

        MainMenuHandler mainMenuHandler = new MainMenuHandler(escapeRoomService, userService, inventoryService, roomService, hintService, decorationService, ticketService, rewardService, certificationService);
        mainMenuHandler.run();
    }
}
