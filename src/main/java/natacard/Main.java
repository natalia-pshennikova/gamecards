package natacard;
import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.PlayCardsFX;
import guru.bug.playcardsfx.Stack;
import guru.bug.playcardsfx.Table;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PlayCardsFX.launch(args, 8, 5, Main::start);
    }

    private static void start(Table table) {
        // создаем стопки
        Stack stack1 = table.createStack(1.0, 0.5, 0.01, 0.0);
        Stack stack2 = table.createStack(4.0, 0.5, 0.00, 0.2);
        Stack stack3 = table.createStack(6.0, 0.5, 0.00, 0.2);

        // создаем карты, параметр true значит, что все карты лицом вниз
        List<Card> cards = table.createPack(true);
        Collections.shuffle(cards); // перемешиваем

        // кладем перемешанные карты в первую стопку
        stack1.setCards(cards);

        // обработчик кликов. Срабатывает при клике на любую стопку
        table.onClick((stack, card) -> {
            // stack - стопка по которой кликнули
            // card  - карта из стопки по которой кликнули.
            //         Может быть null если стопка пустая

            // перемещаем карту, если клик произошел
            // на вторую или третью стопки
            if (stack != stack1) {
                moveLastCard(stack1, stack); // перемещаем
            }
        });
    }


    /**
     * Метод для перемещения карты из одной стопки в другую
     *
     * @param stackFrom исходная стопка
     * @param stackTo   целевая стопка
     */
    private static void moveLastCard(Stack stackFrom, Stack stackTo) {
        // берем список карт, находящихся в стопке stackFrom
        List<Card> source = stackFrom.getCards();

        // ничего не надо делать, если исходная стопка уже пустая
        if (source.isEmpty()) {
            return;
        }

        // берем последнюю карту из исходной стопки
        // и переворачиваем ее лицом вверх
        Card last = source.get(source.size() - 1);
        last.setFaceDown(false);

        // берем список карт целевой стопки
        // и добавляем карту в него
        List<Card> target = stackTo.getCards();
        target.add(last);

        // устанавливаем список карт для в целевую стопку
        stackTo.setCards(target);
    }

}