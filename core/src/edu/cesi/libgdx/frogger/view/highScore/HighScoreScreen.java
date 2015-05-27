package edu.cesi.libgdx.frogger.view.highScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cesi.libgdx.frogger.resources.SettingsManager;

public class HighScoreScreen implements Screen
{
	
	/**
	 * Create and draw HighScore stage
	 * */

	ScoreStage stage;
	SettingsManager settingsManager;
	String level;
	String[] highScore ;
	private Skin skin;
	
	private Texture background;
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	private Viewport viewport;

	
	public HighScoreScreen()
	{
		this.batch = new SpriteBatch();
		
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(1200,800,camera);
		this.viewport.apply();
	      
		this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		this.camera.update();
	
		settingsManager = SettingsManager.getInstance();

		this.background = new Texture(Gdx.files.internal("settingsScreen/settingsBackground1200x800.jpg"));
		level = settingsManager.getLevel();
		
		this.stage = new ScoreStage(settingsManager.getHighScore(level));
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.camera.update();	
		
		batch.begin();
		this.batch.setProjectionMatrix(camera.combined);
		batch.draw(background, 0, 0);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
	      this.viewport.update(width,height);
	      this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.stage.dispose();
		this.skin.dispose();
		this.background.dispose();
		this.batch.dispose();
		System.out.println("HighScoreScren dispose");

	}

}
