package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import src.model.Dice;
import src.model.GameField;
import src.model.Go;
import src.model.Jail;
import src.model.ParkGardenMosque;
import src.model.Player;
import src.model.PropertyUtility;
import src.model.Swap;
import src.model.Tax;
import src.view.GameBoard;

public class PlayerController implements ActionListener {

    private final GameBoard board;
    private final List<GameField> lis;
    private  Dice dice;
    private Player player1;
    private Player player2;

    public PlayerController(GameBoard board) {
        this.board = board;
        lis = board.getField().getAllFields();
		dice = Dice.getDice();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1 = board.getPlayer1();
        player2 = board.getPlayer2();
        if (player1.hasCompleted() || (player2 != null && player2.hasCompleted())) {
            JOptionPane.showMessageDialog(board.getCanvas(), "Cannot move: Game ended!");
            return;
        }
        if (e.getSource().equals(board.getPlayer1Roll())) {
            player1();
        } else if (e.getSource().equals(board.getPlayer2Roll())) {
            player2();
        }

        board.getBankArea().setText(board.getBankArea().getText() + "\n Current Balance: " + board.getBank().getBalance());
        check_end();
    }

    private void player1() {
        if (player1.getBalance() <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over Player 1 balance is zero");
            board.getPlayer2Roll().setVisible(false);
            board.getPlayer1Roll().setVisible(false);
            return;
        }
        if (player1.getCurrentCell().getField().equals("In Jail")) {
            Jail jail = (Jail) player1.getCurrentCell();
            jail.jail(player1);
            board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "Jail tax payed by player 1\n");
        }
        if (board.getPlayer2() != null) {
            board.getPlayer2Roll().setVisible(true);
            board.getPlayer1Roll().setVisible(false);
        }
        int roll = dice.roll();
        board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "\n Player 1 rolled dice: " + roll);
        int index = lis.indexOf(player1.getCurrentCell());
        if (index + roll > lis.size()) {
            player1.deposit(200);
        }
        index = (index + roll) % lis.size();
        player1.move(lis.get(index), index + roll >= lis.size());
        board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "\n Player 1 Moved current cell: "
                + player1.getCurrentCell().toString());
        String s = check(player1);
        board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "\n" + s);
        board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "\nCurrent balance: " + player1.getBalance());

        board.getCanvas().repaint();
    }

    private void player2() {
        if (player2.getBalance() <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over Player 2 balance is zero");
            board.getPlayer2Roll().setVisible(false);
            board.getPlayer1Roll().setVisible(false);
            return;
        }
        if (player2.getCurrentCell().getField().equals("In Jail")) {
            Jail jail = (Jail) player2.getCurrentCell();
            jail.jail(player2);
            board.getPlayer1Area().setText(board.getPlayer1Area().getText() + "Jail tax payed by player 2\n");
        }
        board.getPlayer1Roll().setVisible(true);
        board.getPlayer2Roll().setVisible(false);
        int roll = dice.roll();
        int index = lis.indexOf(player2.getCurrentCell());
        index = (index + roll) % lis.size();
        if (index + roll > lis.size()) {
            player2.deposit(200);
        }
        player2.move(lis.get(index), index + roll >= lis.size());
        board.getPlayer2Area().setText(board.getPlayer2Area().getText() + "\n Player 2 rolled dice: " + roll);
        board.getPlayer2Area().setText(board.getPlayer2Area().getText() + "\n Player 2 Moved current cell: "
                + player2.getCurrentCell().toString());
        board.getPlayer2Area().setText(board.getPlayer2Area().getText() + "\nCurrent balance: " + player2.getBalance());

        String s = check(player2);
        board.getPlayer2Area().setText(board.getPlayer2Area().getText() + "\n" + s);
        board.getCanvas().repaint();
    }

    private int getScore(Player p) {
        if (p == null) {
            return 0;
        }
        int score = 0;
        for (PropertyUtility prop : p.getProperties()) {
            score += prop.getPrice();
        }
        return score + (int) p.getBalance();
    }

    private void check_end() {
        if (!player1.hasCompleted() && !(player2 != null && player2.hasCompleted())) {
            return;
        }
        final Player winner = getScore(board.getPlayer1()) > getScore(board.getPlayer2())
                ? board.getPlayer1() : board.getPlayer2();
        JOptionPane.showMessageDialog(board.getCanvas(), String.format("The winner is: %s", winner.getName()));
    }

    private String check(Player p) {
        GameField field = p.getCurrentCell();
        switch (field.getType()) {
            case "Property":
            case "Utility":
            case "Railroads":
                PropertyUtility prop = (PropertyUtility) field;
                if (prop.isOwned()) {
                    if (prop.getOwner().equals(p)) {
                        return "You own this property";
                    }
                    prop.payRent(p);
                    return p.getName() + " pays rent to owner: " + prop.getOwner().getName();
                } else {
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to buy property?\n"
                            + "Property Name: " + prop.getField()
                            + "Property price: "
                            + prop.getPrice() + "\n" + "Your balance: " + p.getBalance(), "Buy property",
                            JOptionPane.YES_NO_OPTION);
                    if (option == 0) {
                        boolean b = prop.buyProperty(p);
                        if (b) {
                            board.getBank().desposit(prop.getPrice());
                            return "Player 1 own property " + prop.getField();
                        } else {
                            return "Player 1 can't own property " + prop.getField();
                        }
                    }
                }
                break;
            case "Jail":
                Jail jail = (Jail) field;
                if (jail.getField().equals("Go to Jail")) {
                    jail.goToJail(p);
                    return "Player moved to jail";
                } else {
                    board.getBank().desposit(jail.jail(p));
                }
                break;
            case "Tax":
                Tax tax = (Tax) field;
                if (p.equals(player1)) {
                    return tax.payTax(p, player2, board.getBank());
                } else {
                    return tax.payTax(p, player1, board.getBank());
                }
            case "Swap":
                Swap swap = (Swap) field;
                if (p.equals(player1)) {
                    if (player2 != null) {
                        swap.swap(p, player2);
                        return p.getName() + "Has swapped list of properties with " + player2.getName();
                    } else {
                        return p.getName() + "Can't swap property as there is only one player";
                    }
                } else {
                    swap.swap(p, player1);
                    return p.getName() + "Has swapped list of properties with " + player1.getName();
                }

            case "Go":
                return p.getName() + " pass go";
            case "Mosque":
            case "Park":
            case "Garden":
                ParkGardenMosque gardenMosque = (ParkGardenMosque) field;
                board.getBank().desposit(gardenMosque.payRent(p));
                ;
                return "Player paid rent for " + gardenMosque.getField();
            case "Roll again":
                if (player1.equals(p)) {
                    board.getPlayer1Roll().setVisible(true);
                    board.getPlayer2Roll().setVisible(false);
                } else if (player2 != null) {
                    board.getPlayer2Roll().setVisible(true);
                    board.getPlayer1Roll().setVisible(false);
                }
                return p.getName() + " roll again";
        }
        return "";

    }

}
