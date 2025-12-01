package menu;

import certification.CertificationService;
import decoration.DecorationService;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import hint.HintService;
import input.InputHandler;
import room.Room;
import room.RoomService;
import ticket.TicketService;

import java.util.List;

public class EscapeRoomMenuHandler extends EntityMenuHandler<EscapeRoom> {

    private final EscapeRoomService escapeRoomService;
    private final RoomService roomService;
    private final HintService hintService;
    private final DecorationService decorationService;
    private final TicketService ticketService;
    private final CertificationService certificationService;

    public EscapeRoomMenuHandler(EscapeRoom escapeRoom, EscapeRoomService escapeRoomService, RoomService roomService, HintService hintService, DecorationService decorationService, TicketService ticketService, CertificationService certificationService) {
        super(escapeRoom);
        this.escapeRoomService = escapeRoomService;
        this.roomService = roomService;
        this.hintService = hintService;
        this.decorationService = decorationService;
        this.ticketService = ticketService;
        this.certificationService = certificationService;
    }

    @Override
    protected void showMenuOptions() {
        System.out.println("==== ESCAPE ROOM " + entity.getId() + " (" + entity.getName() + ")" + " MENU ====");
        System.out.println("We can do the following:");
        System.out.println("1. Edit escape room data");
        System.out.println("2. Show rooms");
        System.out.println("3. Add room");
        System.out.println("4. Manage room");
        System.out.println("5. Remove room");
        System.out.println("0. Back");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1 -> editEscapeRoomData();
            case 2 -> showAllRooms();
            case 3 -> addRoom();
            case 4 -> manageRoom();
            case 5 -> removeRoom();
            case 0 -> System.out.println("Going back to main menu...");
            default -> System.out.println("Invalid option (" + option + ").");
        }
    }

    private void editEscapeRoomData() {
        System.out.println("Editing data for escape room #" + entity.getId());

        String newName = InputHandler.readString("Enter new name (current: " + entity.getName() + ")");

        try {
            boolean success = escapeRoomService.updateEscapeRoom(entity.getId(), newName);
            System.out.println(success ? "Escape Room Updated" : "Could not update Escape Room");
            if (success) {
                entity.setName(newName);
                System.out.println("Data updated successfully for escape room #" + entity.getId());
            } else {
                System.out.println("Error updating data for escape room #" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error updating data for escape room #" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void addRoom() {
        System.out.println("Adding new room to escape room #" + entity.getId());

        String name = InputHandler.readString("Enter room name");
        int difficultyValue = InputHandler.readInt("Enter room difficulty (1-3)");
        double price = InputHandler.readDouble("Enter room price");

        try {
            boolean success = roomService.addRoom(name, difficultyValue, price, entity.getId());
            if (success) {
                System.out.println("Room added successfully to escape room #" + entity.getId());
            } else {
                System.out.println("Error adding room to escape room #" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error adding room to escape room #" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void manageRoom() {
        int roomId = InputHandler.readInt("Enter room Id");

        Room room = roomService.getRoomById(roomId);

        if (room != null) {
            RoomMenuHandler roomMenu = new RoomMenuHandler(room, roomService, hintService, decorationService, ticketService, certificationService);
            roomMenu.run();
        } else {
            System.out.println("Room #" + roomId + " could not be found");
        }
    }

    private void removeRoom() {
        System.out.println("Removing room from escape room #" + entity.getId());

        int roomId = InputHandler.readInt("Enter room Id");

        try {
            boolean success = roomService.removeRoom(roomId);
            if (success) {
                System.out.println("Room removed successfully from escape room #" + entity.getId());
            } else {
                System.out.println("Error removing room from escape room #" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error removing room from escape room #" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void showAllRooms() {
        List<Room> rooms = roomService.getRoomsByEscapeRoom(entity.getId());

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in this escape room");
        } else {
            System.out.println("Rooms in escape room #" + entity.getId() + " (" + entity.getName() + "):");
            rooms.forEach(System.out::println);
        }
    }
}
