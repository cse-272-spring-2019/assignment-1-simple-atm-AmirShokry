/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm.project;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Amir
 */
public class SimpleATMProjects extends Application {
    private int seekTop=9;
    private int amount;
    private static int balance;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Logicw logic= new Logicw();
        History history = new History();
        
        //Homepage;
        Alert alert = new Alert(AlertType.ERROR);
        Label Login=new Label("Enter your number:");
        Button Enter=new Button("Enter");
        NumberTextField inputPin= new NumberTextField();
        Pane root2=new Pane();
        root2.getChildren().addAll(inputPin,Enter,Login);
        Scene Homepage=new Scene(root2, 500,500);
        Enter.setLayoutX(299);Enter.setLayoutY(250);
        inputPin.setLayoutX(150);inputPin.setLayoutY(250);
        Login.setLayoutX(150);Login.setLayoutY(230);

        //Options page;
         Pane root = new Pane();
         
         Alert outOfHistory = new Alert(AlertType.WARNING);
         outOfHistory.setTitle("Error");
         outOfHistory.setHeaderText("End Reached");
         outOfHistory.setContentText("No more history entries to show");
         
         Alert Withdrawal = new Alert(AlertType.WARNING);
         Withdrawal.setTitle("Error");
         Withdrawal.setHeaderText("Cannot withdraw funds");
         Withdrawal.setContentText("Selected amount exceeds current balanace");
         
         
         Alert enterAmount = new Alert(AlertType.WARNING);
         enterAmount.setTitle("Error");
         enterAmount.setHeaderText("Amount missing");
         enterAmount.setContentText("Please input an amount");
         
         Alert success = new Alert(AlertType.INFORMATION);
         success.setTitle("Successful Transaction");
         success.setHeaderText("Succesful Transaction");
         

         

         Label amountLabel= new Label("");
         Label currentBalance= new Label("");
         Button inquiry = new Button("Inquiry");
         Button toDeposit = new Button("Deposit");
         Button toWithdraw= new Button("Withdraw");
         Button Next = new Button("   Next   ");
         Button Previous = new Button("Previous");
        root.getChildren().addAll(inquiry,amountLabel,currentBalance,Next,Previous,toDeposit,toWithdraw);
        Scene options = new Scene(root, 500, 500);
        amountLabel.setLayoutX(350);amountLabel.setLayoutY(225);
        currentBalance.setLayoutX(350);currentBalance.setLayoutY(255);
        inquiry.setLayoutX(150);inquiry.setLayoutY(220);
        inquiry.setMinHeight(55);
        toDeposit.setLayoutX(205);toDeposit.setLayoutY(250);
        toWithdraw.setLayoutX(263);toWithdraw.setLayoutY(250);
        Next.setLayoutX(268);Next.setLayoutY(220);
        Previous.setLayoutX(205);Previous.setLayoutY(220);
        
        //Amount page
        Pane root3= new Pane();
        NumberTextField inputAmount= new NumberTextField();
        Button deposit = new Button("Deposit");
        Button withdraw = new Button("Withdraw");
        Button back=new Button("Back");
        root3.getChildren().addAll(inputAmount,deposit,withdraw,back);
        Scene amounts=new Scene(root3,500,500);
        inputAmount.setLayoutX(160);inputAmount.setLayoutY(250);
        deposit.setLayoutX(310);deposit.setLayoutY(250);
        withdraw.setLayoutX(310);withdraw.setLayoutY(250);
        deposit.setVisible(false);
        withdraw.setVisible(false);
        //ToDeposit
                    toDeposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentBalance.setText("");
               primaryStage.setScene(amounts);
               deposit.setVisible(true);
            }
        });
                     toWithdraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentBalance.setText("");
               primaryStage.setScene(amounts);
               withdraw.setVisible(true);
            }
        });
        //Back
          back.setOnAction(e-> primaryStage.setScene(options));          
        //Enter Button
            Enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if(Valid.validate(inputPin.getText()))
                   primaryStage.setScene(options);
               else{
                   alert.setTitle("Error");
                   alert.setHeaderText("Incorrect input");
                   alert.setContentText("Wrong PIN");
                   alert.showAndWait();
               }
            }
        });
            //Inquiry button
          inquiry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                balance= logic.getCurrentBalance();
                history.stack(balance,"Balance inquiry ");
               currentBalance.setText("Current Balanace : "+balance);
               seekTop=history.getTop();
               
              
            }
        });
          
          //Deposit button
           deposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                amountLabel.setText("");
                if(inputAmount.getText()==null || inputAmount.getText().trim().isEmpty())
                    enterAmount.showAndWait();
                else{
                 amount= Valid.convert(inputAmount.getText());
                logic.deposit(amount);
                history.stack(amount,"Deposit");
                seekTop=history.getTop();
                success.showAndWait();
               }
              
            }
        });
           //Withdraw
          withdraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                amountLabel.setText("");
                if(inputAmount.getText()==null || inputAmount.getText().trim().isEmpty())
                    enterAmount.showAndWait();
                else{
                 amount= Valid.convert(inputAmount.getText());
                logic.withdraw(amount);
                if(logic.getValidwithdraw()==1){
                history.stack(amount,"Withdraw");
                seekTop=history.getTop();
                success.showAndWait();
                }
                else{
                    Withdrawal.showAndWait();
                }
               }
              
            }
        });
 //Previous button
          Previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
               Next.setVisible(true);
               if(seekTop==9){
                    outOfHistory.showAndWait();
                    Previous.setDisable(false);}
               else if(seekTop==0){
                    outOfHistory.showAndWait();
                    Previous.setDisable(false);}
               
                else { 
                    seekTop--;
                amountLabel.setText(history.Type.get(seekTop)+": " +history.Amount.get(seekTop)  );}
            }
        });
          
          //Next button
          Next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(seekTop==0){
                 amountLabel.setText(history.Type.get(seekTop)+": " +history.Amount.get(seekTop));
  }
                if(seekTop>=history.Amount.size()-1){
                 Next.setDisable(false);
                outOfHistory.showAndWait();}
               
              else{ seekTop++;
                amountLabel.setText(history.Type.get(seekTop)+": " +history.Amount.get(seekTop));
                }
            }
        });
          
          
        primaryStage.setTitle("Simple ATM!");
        primaryStage.setScene(Homepage);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
    

