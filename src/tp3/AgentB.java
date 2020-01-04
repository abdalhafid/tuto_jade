package tp3;

import java.io.IOException;

import javax.swing.JOptionPane;

import tp3.AgentA.message;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgentB extends jade.core.Agent{
	public void setup()
	{
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {
				MessageTemplate mt = MessageTemplate.MatchConversationId("1");
				ACLMessage messageA = blockingReceive(mt);
				if(messageA !=null)
				{
					JOptionPane.showMessageDialog(null, messageA.getContent());
					ACLMessage reply = new ACLMessage(ACLMessage.CFP);
					reply.addReceiver(new AID("A", AID.ISLOCALNAME));
					reply.setConversationId("2");
					reply.setContent(getLocalName()+" dit à A: Bonjour , mois c'est "+ getLocalName());
					reply.setReplyWith("reply" + System.currentTimeMillis());
					send(reply);
					MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchConversationId("2"), MessageTemplate.MatchInReplyTo(reply.getReplyWith()));
					MessageTemplate mt2 = MessageTemplate.MatchConversationId("3");
					ACLMessage messageA1= blockingReceive(mt2);

					if(messageA1 !=null)
					{
						
						try{
							message mess = (message) messageA1.getContentObject();
							JOptionPane.showMessageDialog(null, mess.messag);
							float rep =mess.x+mess.y;
							ACLMessage Reply2 = messageA1.createReply();
							Reply2.setConversationId("4");
							Reply2.setContent(getLocalName()+" dit à A: Le résultat est "+String.valueOf(rep));
							send(Reply2);
							MessageTemplate mt3 = MessageTemplate.and(MessageTemplate.MatchConversationId("4"), MessageTemplate.MatchInReplyTo(Reply2.getReplyWith()));
						}catch(NumberFormatException e)
						{
							
						} catch (UnreadableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						doDelete();
					}else
						block();
				}else 
					block();
				
			}
			
		});
		
		
	}
}
