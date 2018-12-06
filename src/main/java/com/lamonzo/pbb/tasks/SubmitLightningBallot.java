@Component
@Scope("prototype")
@Slf4j
public class SubmitLightningBallot extends Task<Boolean>{
	 //================================================================================================================//
    //== FIELDS ==
    private final DataModel dataModel;
    private final BrowserUtil browserUtil;
	private final SettingsController settingsController;
	
	//================================================================================================================//
    //== CONSTRUCTORS ==
	@Autowired
	public SubmitLightningBallot(DataModel dataModel, BrowserUtil browserUtil, SettingsController settingsController){
		this.dataModel = dataModel;
		this.browserUtil = browserUtil;
		this.settingsController = settingsController;
	}
	
	//================================================================================================================//
    //== PUBLIC METHODS ==
	@Override
	public Boolean call(){	
		Map<Position, List<Player>> finalBallotMap;
		
		Browser browser = browserUtil.getBrowser();
		
		//TODO: CREATE AN ABSTRACT CLASS THAT HAS THE VISITBALLOTPAGE FUNCTIONALITY IN IT AND HAVE BOTH SUBMIT BALLOTS
		//EXTEND THAT METHOD AND THEN SHARE THAT FUNCTIONALITY. ADD A BOOLEAN PARAM TO THE METHOD AND IF FALSE THEN 
		//AUTO SELECT THE CASE TO FOLLOW THE DIRECT LINK WHICH WILL BE USED BY THIS VERSION.
		visitBallotPage(browser, false);
		
		//IF AUTOFILL IS TURNED OFF THEN WE ONLY NEED TO BUILD THE MAP THE FIRST TIME AND IT SHOULD BE DONE HERE
		//IF AUTO FILL IS TUREND ON THEN WE NEED TO BUILD THE MAP WITH EVRY NEW REQUEST
		if(!dataModel.getIsAutoFill().get())
			finalBallotMap = buildFinalBallot();

		
		//GET THE STRING VALUE OF THE VOTE GOALS, DOING IT LIKE THIS DECOUPLES 
		//THE VALUES OF THE SLIDER SO I WON'T NEED TO ALTER THIS CODE IF I CHANGE THE VALUES
		double value = dataModel.getVotingGoals().get();
        String voteSliderString = settingsController.getVoteGoalSlider().getLabelFormatter().toString(value).trim();
		boolean unlimited = voteSliderString.equalsIgnoreCase(settingsController.getUNLIMITED());
		
		//CONTINUE PROCESSING VOTES FOREVER (UNLIMITED) OR UNTIL VOTE GOALS ARE REACHED
		while(unlimited || dataModel.getSuccessCount() < Integer.parseInt(voteSliderString)){
			try{
				JavascriptExecutor jse = (JavascriptExecutor) browser.driver;
				WebDriverWait wait = new WebDriverWait(browser.driver, 8)
				
				//BUILD A NEW LIST OF EXTRA VOTES IF AUTOFILL IS SELECTED
				if(dataModel.getIsAutoFill().get())
					finalBallotMap = buildFinalBallot();
				
				for(Position pos : finalBallotMap.keySet()){
					jse.executeScript("window.scrollTo(0, 0)");
					Element tab = browser.doc.findFirst(pos.getTabHtmlLink());
					
					tab.click();
					log.info("Processing Position Tab: " + pos.getPositionName());
					
					for(Player player : finalBallotMap.get(pos)){
						//CONFIGURE XPATH STRINGS
						String playerDivXPath = ScrapingConstants.PLAYER_DIV_XPATH_PREFIX + player.getHtmlIdentifier()
							+ ScrapingConstants.PLAYER_DIV_XPATH_SUFFIX;
						String playerVoteXPath = playerDivXPath + ScrapingConstants.PLAYER_VOTE_BTN_XPATH;
					
						int attempt = 0;
						String scroll = "0";
						
						//TODO: ABSTRACT THE MAX ATTEMPTS NUMBER SOMEWHERE AND TO ALLOW FOR EASY MODIFICATION
						//ATTEMPT TO FIND PLAYERS ON THE PAGE FOR MAX OF N TIMES WHILE SCROLLING DOWN FURTHER WITH EACH ATTEMPT
						while(attempt < 10){
							try{
								jse.executeScript("window.scrollTo(0, " + scroll + ")"); //SCROLL TO POSITION ON PAGE
								
								WebElement playerDiv = wait.until(d -> d.findElement(By.xpath(playerDivXPath)));
								Actions actions = new Actions(browser.driver);
								actions.moveToElement(playerDiv).perform(); //SIMULATE MOUSE HOVER EFFECT
								
								WebElement voteBtn = wait.until(d -> d.findElement(By.xpath(playerVoteXPath)));
								voteBtn.click(); //CLICK THE VOTE BUTTON THAT APPEARS ON HOVER
								break;
							}
							catch(ElementNotSelectableException | ElementNotInteractableException |  TimeoutException e){
								if(++attempt < 10){
									log.warn("Unable to select player or vote button for: " 
										+ player.getName()+ " | Attempt " + attempt);
									
									int value = Integer.parseInt(scroll) + 500;
									scroll = Integer.toString(value);	
								}
								else
									log.error("Exceeded attempts to find " + player.getName() + " | "
										+ player.getPosition().getPositionName() + " | Moving to next player");
							}
						}
					}
				}
				
				//SUBMIT THE BALLOT FOR THE FINAL POSITION
				Element submitButton = browser.doc.findFirst(ScrapingConstants.SUBMIT_BUTTON);
				submitButton.click();
				
				//CLICK THE VOTE AGAIN BUTTON & INCREMENT COUNT TO RESTART THE PROCESS
				WebElement voteAgainButton = wait.until(d -> d.findElement(By.xpath(ScrapingConstants.VOTE_AGAIN_BTN_XPATH)));
				
				if(browser.getLocation().equalsIgnoreCase(ScrapingConstants.VOTING_THANK_YOU_PAGE_URL))
					dataModel.getSuccessCount() += dataModel/getSuccessCount() + 1;

				voteAgainButton.click();
			}
			catch(JauntiumException | InterruptedException ex){
				log.warn("Error visiting page and submitting ballot " + ex.getMessage());
				browser.quit();
				browser = browserUtil.getBrowser();
			}
			catch(Exception e){
				log.warn("Unknown Exception Caught");
				e.printStackTrace();
				browser.quit();
				browser = browserUtil.getBrowser();
			}
		}
		
		return true;
	}
}