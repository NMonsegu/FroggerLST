package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.UserMovements;
import edu.cesi.libgdx.frogger.utils.Constants;

public class Player extends Entity
{
	
	   
	private final int STAY = 0;
	private final int UP = 1;
	private final int DOWN = 2;
	private final int LEFT = 3;
	private final int RIGHT = 4;
	
	//CHANGE TO PRIVATE
	public boolean isFloating = false;
	public boolean isMouving = false;

	private int direction = STAY;
    private Animation walkAnimation;    
    private Animation walkStayAnimation;
    private Animation backAnimation; 
    private Animation backStayAnimation;
    private Animation rightAnimation;
    private Animation rightStayAnimation;
    private Animation leftAnimation;
    private Animation leftStayAnimation;

    private boolean isCollide = false;
    private boolean isDead = false;
    
    private Rectangle advancedCollisionRectangle;
    
	public Player()
	{  
        bounds = new Rectangle();            
        bounds.x = 600;
        bounds.y = 0;                         
        bounds.width = 60;
        bounds.height = 70;
        
        advancedCollisionRectangle = new Rectangle();
        advancedCollisionRectangle.width  = 40;
        advancedCollisionRectangle.height = 32;
        
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = walkAnimation;
        this.mouvement = new UserMovements();
        mouvement_float = new UserMovements();
        
        life = Constants.DEFAULT_LIFE;
	}
    
    private void loadAnimation()
    {
		
		walkAnimation = new Animation(0.1f,imageManager.getPlayerBackTextureRegion());
		walkStayAnimation = new Animation(0.1f,imageManager.getPlayerBackTextureRegion()[1]);

		backAnimation = new Animation(0.1f,imageManager.getPlayerWalkTextureRegion());
		backStayAnimation = new Animation(0.1f,imageManager.getPlayerWalkTextureRegion()[1]);
		
		rightAnimation = new Animation(0.1f,imageManager.getPlayerRightTextureRegion());
		rightStayAnimation = new Animation(0.1f,imageManager.getPlayerRightTextureRegion()[1]);
		
		leftAnimation = new Animation(0.1f,imageManager.getPlayerLeftTextureRegion());
		leftStayAnimation = new Animation(0.1f,imageManager.getPlayerLeftTextureRegion()[1]);
	}
    
    @Override
	public void move(int velocity)
    {
    	if(!isDead)
    	{
			mouvement.move(bounds, velocity);
    	}
    }
    
    public boolean moveLeft = false;
    public boolean moveRight = false;
    
    /**
     *Used to move the player when he is floating
     * */
    public void moveLeft(int velocity)
    {
    	moveLeft = true;
    	moveRight = false;
    	mouvement_float.moveLeft(bounds, velocity);
    }
    
    /**
     *Used to move the player when he is floating
     * */
    public void moveRight(int velocity)
    {	moveLeft = false;
    	moveRight = true;
    	mouvement_float.moveRight(bounds, velocity);
    }
    
    protected UserMovements mouvement_float;
	
    @Override
	public void update()
	{
		if(!isDead)
		{
			checkKeyDown();
			checkKeyUp();
		}
		else
		{
			if(Gdx.input.isKeyPressed(Keys.ENTER))
			{
				direction = UP;
				currentAnimation = walkStayAnimation;
				isDead = false;
			}
		}
	} 
	
    /**
     * Update the collision rectangle depending upon his direction
     * */
	public void updateAdvancedCollisionRectangle()
	{
		if(direction == UP || direction == DOWN)
		{
			this.advancedCollisionRectangle.x = this.bounds.x + 13;
	        advancedCollisionRectangle.width  = 40;
	        advancedCollisionRectangle.height = 32;
		}else if(direction == LEFT)
		{
			this.advancedCollisionRectangle.x = this.bounds.x + 17;
	        advancedCollisionRectangle.width  = 24;
	        advancedCollisionRectangle.height = 32;
		}else if (direction == RIGHT)
		{
			this.advancedCollisionRectangle.x = this.bounds.x + 25;
	        advancedCollisionRectangle.width  = 24;
	        advancedCollisionRectangle.height = 32;
		}
		advancedCollisionRectangle.y = bounds.y ;	
	}
	
	private void checkKeyDown()
	{
			
		if(!isDead || !isCollide){
			stateTime += Gdx.graphics.getDeltaTime(); 
			if(Gdx.input.isKeyPressed(Keys.UP)){
				direction = UP;
				currentAnimation = walkAnimation;
				isMouving= true;
				
				moveLeft = false;
		    	moveRight = false;
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)){
				direction = DOWN;
				currentAnimation = backAnimation;
				isMouving= true;
				
				moveLeft = false;
		    	moveRight = false;
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				direction = LEFT;
				currentAnimation = leftAnimation;
				isMouving= true;	
				
		    	moveLeft = true;
		    	moveRight = false;
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				direction = RIGHT;
				currentAnimation = rightAnimation;
				isMouving= true;
				
		    	moveLeft = false;
		    	moveRight = true;
			}
		}
	}
	
	private void checkKeyUp()
	{
		if(!isDead && !isCollide)
		{
			if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT))
			{
				switch (direction) 
				{
		            case UP:  
		            	currentAnimation = walkStayAnimation ;
		            	isMouving= false;
		            	
						moveLeft = false;
				    	moveRight = false;
		                break;
		            case DOWN:  
		            	currentAnimation = backStayAnimation;
		            	isMouving= false;
		            	
						moveLeft = false;
				    	moveRight = false;
		                break;
		            case LEFT:  
		            	currentAnimation = leftStayAnimation;
		            	isMouving= false;
		            	
						moveLeft = false;
				    	moveRight = false;
		                break;
		            case RIGHT:  
		            	currentAnimation = rightStayAnimation;
		            	isMouving= false;
		            	
						moveLeft = false;
				    	moveRight = false;
		                break;
		            default: currentAnimation = walkStayAnimation;
		                     break;
				}
			}
		}
	}



	/**
	 *Return true if the player has 0 life else return false
	 * */
	public boolean die()
	{
		if(this.life == 0)
		{
			isDead = true;
			System.out.println("you're dead !");
			
			this.life = Constants.DEFAULT_LIFE;
			return true;
		}else
		{
			return false;
		}
	}

	
	//GET - SET
	
	public Rectangle getAdvancedCollisionRectangle()
	{
		return this.advancedCollisionRectangle;
	}
	
	public void setAdvancedCollisionRectangleX( float x)
	{
		//return this.advancedCollisionRectangle;
		this.advancedCollisionRectangle.x = x;
	}
	
	public void setAdvancedCollisionRectangleY(float y)
	{
		//return this.advancedCollisionRectangle;
		this.advancedCollisionRectangle.y = y;

	}
	
	public int getDirection() {
		return direction;
	}


}
