package msg;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.JOptionPane;
public class Second extends Agent {
	protected void setup()
	{
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {
				ACLMessage msg = receive();
				if(msg!=null)
				{
					JOptionPane.showMessageDialog(null, "MSG Recu ! \nContenu: "+msg.getContent());
				}/*else{
					block();
				}*/
			}
			
		});
	}
}
