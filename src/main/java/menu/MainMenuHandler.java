package menu;

import Inventory.Inventory;
import Inventory.InventoryService;
import decoration.Decoration;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import hint.Hint;
import input.InputHandler;
import room.Room;
import ticket.TicketService;
import user.User;
import user.UserService;

import java.util.List;

public class MainMenuHandler extends AbstractMenuHandler {

    private final EscapeRoomService escapeRoomService = new EscapeRoomService();
    private final UserService userService = new UserService();
    private final TicketService ticketService = new TicketService();
    private final InventoryService inventoryService = new InventoryService();


    @Override
    protected void showMenuOptions() {
        System.out.println("We can do the following:");
        System.out.println("1. Add escape room");
        System.out.println("2. Manage escape room");
        System.out.println("3. Remove escape room");
        System.out.println("4. Add user");
        System.out.println("5. Manage user");
        System.out.println("6. Remove user");
        System.out.println("7. Check revenues");
        System.out.println("8. Send notification to subscribed users");
        System.out.println("9. Show full inventory");
        System.out.println("10. Show inventory total value");
        System.out.println("0. Exit");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                addEscapeRoom();
                break;
            case 2:
                manageEscapeRoom();
                break;
            case 3:
                deleteEscapeRoom();
                break;
            case 4:
                addUser();
                break;
            case 5:
                manageUsers();
                break;
            case 6:
                removeUser();
                break;
            case 7:
                showTotalRevenue();
                break;
            case 8:
                sendNotification();
                break;
            case 9:
                showFullInventory();
                break;
            case 10:
                getInventoryTotalValue();
                break;
            case 0:
                System.out.println("See you soon!");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void addEscapeRoom() {
        String name = InputHandler.readString("Escape Room name: ");
        boolean create = escapeRoomService.createEscapeRoom(name);

        System.out.println(create ? "Escape Room created!" : "Could not create Escape Room");
    }

    private void manageEscapeRoom() {

        List<EscapeRoom> rooms = escapeRoomService.getEscapeRooms();
        if (rooms.isEmpty()) {
            System.out.println("No Escape Rooms available");
            return;
        }

        System.out.println("==== Escape Rooms ====");
        for (EscapeRoom r : rooms) {
            System.out.println(r.getId() + " - " + r.getName());
        }

        int id = InputHandler.readInt("Enter ID to manage: ");
        EscapeRoom select = escapeRoomService.getEscapeRoomById(id);

        if (select == null) {
            System.out.println("Escape room not found.");
            return;
        }
        new EscapeRoomMenuHandler(select).run();
    }

    private void deleteEscapeRoom() {
        int id = InputHandler.readInt("Enter ID to delete: ");

        try {
            boolean ok = escapeRoomService.deleteEscapeRoom(id);
            System.out.println(ok ? "Escape Room removed!" : "Could not delete Escape Room");

        } catch (Exception error) {
            System.out.println("Error deleting Escape Room: " + error.getMessage());
        }
    }

    private void addUser() {
        System.out.println("==== ADD NEW USER ====");

        String name = InputHandler.readString("Enter first name: ");
        String surname = InputHandler.readString("Enter surname: ");
        String email = InputHandler.readString("Enter email: ");

        try {
            boolean create = userService.addUser(name, surname, email);
            System.out.println(create ? "User created successfully!" : "Failed to create user. Please try again.");


        } catch (Exception error) {
            System.out.println("Error creating user: " + error.getMessage());
        }
    }

    private void manageUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users available");
            return;
        }

        System.out.println("==== USERS ====");
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName() + " " + user.getSurnames());
        }

        int id = InputHandler.readInt("Enter ID user to manage: ");
        User selected = userService.getUserById(id);

        if (selected == null) {
            System.out.println("User not found.");
            return;
        }
        new UserMenuHandler(selected).run();
    }

    private void removeUser() {
        int id = InputHandler.readInt("Enter ID user to delete: ");

        try {
            boolean ok = userService.deleteUser(id);
            System.out.println(ok ? "User removed!" : "Could not delete user");
        } catch (Exception error) {
            System.out.println("Error deleting user: " + error.getMessage());
        }
    }

    private void showTotalRevenue() {
        try {
            double totalRevenue = ticketService.getTicketsTotalRevenue();

            System.out.println("==== TOTAL REVENUES ====");
            if (totalRevenue > 0) {
                System.out.printf("Total income: %.2f €\n", totalRevenue);
            } else {
                System.out.println("No revenues found. There might be no tickets sold yet.");
            }

        } catch (Exception error) {

            System.out.println("Error retrieving total revenue: " + error.getMessage());
        }
    }

    private void sendNotification() {
        try {
            List<User> users = userService.getUsers();

            if (users.isEmpty()) {
                System.out.println("There are no users in the system.");
                return;
            }

            String notification = InputHandler.readString("Enter the notification message: ");

            if (notification.trim().isEmpty()) {
                System.out.println("Error: Notification message cannot be empty.");
                return;
            }

            userService.notifySubscribers(notification);
            System.out.println("Notification sent to all subscribed users.");
        } catch (Exception error) {
            System.out.println("An error ocurred while sending the notification: " + error.getMessage());
        }
    }


    private void showFullInventory() {
        try {
            Inventory inventory = inventoryService.getFullInventory();

            boolean isEmpty = inventory.rooms().isEmpty() && inventory.decorations().isEmpty() && inventory.hints().isEmpty();

            if (isEmpty) {
                System.out.println("The inventory is completely empty.");
                return;
            }

            System.out.println("==== FULL INVENTORY ====");

            System.out.println("==== Rooms ====");
            if (inventory.rooms().isEmpty()) {
                System.out.println("No rooms available.");
            } else {
                for (Room room : inventory.rooms()) {
                    System.out.println(room.getId() + " - " + room.getName() + " - Price: " + room.getPrice() + " €");
                }
            }

            System.out.println("==== Decorations ====");
            if (inventory.decorations().isEmpty()) {
                System.out.println("No decorations available.");
            } else {
                for (Decoration decoration : inventory.decorations()) {
                    System.out.println(decoration.getId() + " - " + decoration.getName() + " - Value: " + decoration.getValue() + " €");
                }
            }

            System.out.println("==== Hints ====");
            if (inventory.hints().isEmpty()) {
                System.out.println("No hints available.");
            } else {
                for (Hint hint : inventory.hints()) {
                    System.out.println(hint.getId() + " - " + hint.getText() + " - Value: " + hint.getValue() + " €");
                }
            }

        } catch (Exception e) {
            System.out.println("Error retrieving inventory: " + e.getMessage());
        }
    }

    private void getInventoryTotalValue() {
        try {
            Inventory inventory = inventoryService.getFullInventory();

            boolean isEmpty = inventory.rooms().isEmpty() && inventory.decorations().isEmpty() && inventory.hints().isEmpty();

            if (isEmpty) {
                System.out.println("The inventory is completely empty.");
                return;
            }

            double totalInventoryValue = inventory.getInventoryValue();

            System.out.println("==== INVENTORY TOTAL VALUE ====");
            if (totalInventoryValue > 0) {
                System.out.printf("Total inventory value: %.2f €\n", totalInventoryValue);
            } else {
                System.out.println("No value found. The inventory might be empty.");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving inventory total value: " + e.getMessage());
        }
    }
}


