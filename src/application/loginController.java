<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.ChoiceBox?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.PasswordField?>
<?import javafx.scene.control.Separator?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.text.Font?>

<AnchorPane prefHeight="387.0" prefWidth="537.0" xmlns="http://javafx.com/javafx/8.0.141" xmlns:fx="http://javafx.com/fxml/1" fx:controller="application.loginController">
   <children>
      <Separator layoutX="3.0" layoutY="194.0" prefHeight="142.0" prefWidth="535.0" />
      <TextField fx:id="email" layoutX="146.0" layoutY="48.0" prefHeight="31.0" prefWidth="277.0" promptText="Enter valid email addresss" />
      <PasswordField fx:id="password" layoutX="146.0" layoutY="103.0" prefHeight="31.0" prefWidth="277.0" promptText="*******" />
   	  <ChoiceBox fx:id="choices" layoutX="146.0" layoutY="157.0" prefHeight="31.0" prefWidth="277.0" />
      <Button fx:id="signin" layoutX="235.0" layoutY="211.0" mnemonicParsing="false" text="Sign in " />
      <Button fx:id="signup" layoutX="235.0" layoutY="328.0" mnemonicParsing="false" text="Sign up" />
      <Label alignment="CENTER" graphicTextGap="5.0" layoutX="28.0" layoutY="50.0" prefHeight="21.0" prefWidth="79.0" text="Email">
         <font>
            <Font size="18.0" />
         </font>
      </Label>
      <Label alignment="CENTER" graphicTextGap="5.0" layoutX="28.0" layoutY="107.0" prefHeight="21.0" prefWidth="79.0" text="Password">
         <font>
            <Font size="18.0" />
         </font>
      </Label>
      <Label layoutX="118.0" layoutY="282.0" prefHeight="27.0" prefWidth="304.0" text="Not already a user? Sign up Instead!">
         <font>
            <Font size="18.0" />
         </font>
      </Label>
      <Label alignment="CENTER" graphicTextGap="5.0" layoutX="28.0" layoutY="157.0" prefHeight="27.0" prefWidth="89.0" text="User Type">
         <font>
            <Font size="18.0" />
         </font>
      </Label>
      <Label fx:id="error" layoutX="168.0" layoutY="14.0" prefHeight="21.0" prefWidth="205.0" text="Please enter valid credentials!" textFill="#f54c4c" />
      <Label fx:id="nouser" layoutX="138.0" layoutY="14.0" prefHeight="21.0" prefWidth="294.0" text="User does not exist! Please sign up instead" textFill="#f54c4c" />
   </children>
</AnchorPane>
