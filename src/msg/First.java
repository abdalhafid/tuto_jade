package msg;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class First extends Agent {
	protected void setup()
	{
		addBehaviour(new OneShotBehaviour(){

			@Override
			public void action() {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("Second", AID.ISLOCALNAME));
				//String message = JOptionPane.showInputDialog("Enetrer le message: ");
				msg.setContent("Bonjour l'ami, le message d'utilisateur est: ");
				send(msg);
			}
			
		});
	}
}
