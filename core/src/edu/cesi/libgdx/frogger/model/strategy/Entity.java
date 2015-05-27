package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.resources.ImagesManager;

/**
 * Main game item
 * */
public abstract class Entity 
{
   
	 	protected Animation currentAnimation;
		protected Rectangle bounds;
	    protected float stateTime;
	    protected ImagesManager imageManager = ImagesManager.getInstance();
	    protected int life;

		protected MovementBehavior mouvement = new LinearMovementsLeft();
		protected RenderBehavior render = new BasicRender();

	    /**
	     * Update the item position
	     * */
		public void move(int velocity){
	        mouvement.move(bounds, velocity);
	    }
	    
		/**
		 * use the current render to draw the item
		 * */
	    public void drawAnimation(SpriteBatch batch){
	    	render.draw(batch,bounds,currentAnimation, stateTime);
	    }
	     
	    
	    /**
	     * Need to be overrided
	     * */
	    public void update()
	    {
	    	
	    }

		//GET - SET
	    public Animation getCurrentAnimation() {return currentAnimation;}
		
		public void setCurrentAnimation(Animation currentAnimation) {this.currentAnimation = currentAnimation;}
		
	    public void setMovement(MovementBehavior newMovement){this.mouvement = newMovement;}
		  
	    public void setRender(RenderBehavior newRender){this.render = newRender;}
	    
	    public float getStateTime() {
			return stateTime;
		}

		public void setStateTime(float stateTime) {
			this.stateTime = stateTime;
		}
		
	    public Rectangle getBounds() {
			return bounds;
		}

		public void setBounds(Rectangle bounds) {
			this.bounds = bounds;
		}
		
		public void setWidthBounds(int bound){
			this.bounds.width = bound;
		}
		
		public void setHeightBounds(int bound){
			this.bounds.height = bound;
		}
		
	    public float getWidht(){
	    	return bounds.width;
	    }
	    
	    public float getHeight(){
	    	return bounds.height;
	    }
	 	     
	    public Rectangle getPosition(){
	    	return this.bounds;
	    }
	    
	    public void setPosition(float x, float y){
	    	this.bounds.x = x;
	    	this.bounds.y = y;
	    }
	    
		public int getLife() 
		{
			return life;
		}
		
		public void setLife(int life)
		{
			this.life = life;
		}
}