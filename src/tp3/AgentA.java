package tp3;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentA extends jade.core.Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup()
	{
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {
				ACLMessage messageA = new ACLMessage(ACLMessage.CFP);
				messageA.addReceiver(new AID("B", AID.ISLOCALNAME));
				messageA.setConversationId("1");
				messageA.setContent(getLocalName()+" dit à B : Bonjour , je m'appelle"+ getLocalName()+", et toi?");
				messageA.setReplyWith("cfp2" + System.currentTimeMillis());
				send(messageA);
				
				MessageTemplate mt=MessageTemplate.and(MessageTemplate.MatchConversationId("1"), MessageTemplate.MatchInReplyTo(messageA.getReplyWith()));
				MessageTemplate mt1=MessageTemplate.MatchConversationId("2");
				ACLMessage messageB = blockingReceive(mt1);
				
				
				if(messageB != null)
				{
					JOptionPane.showMessageDialog(null, messageB.getContent());
					String x = JOptionPane.showInputDialog(getLocalName()+" dit à l'utilisateur: Entrer la valeur de x: ");
					String y = JOptionPane.showInputDialog(getLocalName()+" dit à l'utilisateur: Entrer la valeur de y: ");
					ACLMessage messageA1 = new ACLMessage(ACLMessage.CFP);
					messageA1.addReceiver(new AID("B", AID.ISLOCALNAME));
					message mmm = new message(getLocalName()+" dit à B calcule: "+x+" + "+y, Float.parseFloat(x), Float.parseFloat(y));
					try {
						messageA1.setContentObject(mmm);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					messageA1.setConversationId("3");
					messageA1.setReplyWith("messageA1" + System.currentTimeMillis());
					
					send(messageA1);
					MessageTemplate mt3 = MessageTemplate.and(MessageTemplate.MatchConversationId("3"), MessageTemplate.MatchInReplyTo(messageA1.getReplyWith()));
					MessageTemplate mt4 = MessageTemplate.MatchConversationId("4");
					ACLMessage res = blockingReceive(mt4);
					if(res!=null)
					{
						JOptionPane.showMessageDialog(null, res.getContent());
						doDelete();
					}else
						block();
				}else
					block();
			}	
		});
		
		
		
	}
	public class message implements Serializable {
		public String messag;
		public float x;
		public float y;
		public message(String m, float X, float Y)
		{
			messag = m;
			x = X;
			y = Y;
		}
	}
	
}


