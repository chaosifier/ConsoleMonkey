package com.consolemonkey;

import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;
import com.consolemonkey.gamemanager.Game;
import com.consolemonkey.genarator.SentenceGenerator;
import com.consolemonkey.model.GameSession;
import com.consolemonkey.model.Player;

import java.util.List;

public class Orchestrator {
    private ConsoleManager consoleManager;
    private String userName;
    private Game gameSession;
    private PlayerController pController;
    private Player player;
    private SentenceGenerator generator;
    private int defaultNoOfWords = 30;

    public Orchestrator() {
        consoleManager = ConsoleManager.getInstance();

        generator = new SentenceGenerator();
    }

    public void begin() throws Exception {
        welcome();

        // allow passing username from args to skip this step
        userName = getUserName();
        greetUser();

        // check if user config exists
        // if does, get detail model object and set to a private variable
        pController = new PlayerController();
        player = pController.readPlayerData(userName);

        while (true)
            showMainMenu(player);
    }

    private void trainSentenceGenerator() {
        var trainingSentences = List.of(
                "Quantum computing, a revolutionary field blending quantum mechanics and computer science, has sparked immense curiosity and promise. Unlike classical computers that use binary bits, quantum computers leverage quantum bits or qubits, which can exist in multiple states simultaneously. This allows for parallel processing, potentially solving complex problems much faster than traditional computers. From cryptography to drug discovery and optimization conundrums, the applications of quantum computing are vast and transformative, heralding a new era of computational power.",
                "Bonsai, the ancient Japanese art of growing miniature trees in containers, encapsulates harmony between nature and human intervention. Through meticulous pruning, wiring, and cultivation techniques, bonsai artists create living sculptures that evoke a sense of tranquility and agelessness. Each bonsai tells a story, reflecting the passage of time and the artistry of its caretaker. Beyond its aesthetic appeal, bonsai cultivation fosters patience, mindfulness, and a profound appreciation for the beauty of impermanence.",
                "Dark matter, an enigmatic substance that comprises approximately 27% of the universe, continues to elude direct detection and comprehension. Despite its pervasive influence on cosmic structures, its nature remains elusive, sparking intense scientific inquiry and speculation. Astrophysicists employ a myriad of techniques, from gravitational lensing to particle physics experiments, in hopes of unraveling its secrets. Understanding dark matter not only promises to illuminate the fundamental nature of the cosmos but also challenges our perceptions of reality on a cosmic scale.",
                "In a world increasingly reliant on pharmaceuticals, the resurgence of interest in traditional herbal remedies reflects a desire for holistic approaches to health and wellness. From Ayurveda in India to Traditional Chinese Medicine (TCM) and indigenous healing practices, these ancient systems offer a treasure trove of botanical knowledge passed down through generations. With growing concerns about the side effects of synthetic drugs and antibiotic resistance, exploring the therapeutic potential of plant-based medicines holds promise for addressing modern healthcare challenges while honoring centuries-old wisdom.",
                "Vertical farming, a cutting-edge agricultural practice, challenges traditional notions of farming by utilizing vertical space in urban environments to grow crops indoors. By employing hydroponic or aeroponic systems and LED lighting, vertical farms can produce high yields of fresh produce year-round with minimal water and land usage. This sustainable approach to agriculture not only addresses food security issues but also reduces transportation costs and carbon emissions associated with conventional farming practices, paving the way for a more resilient and eco-friendly food system.",
                "Urban exploration, often abbreviated as urbex, beckons adventurers to explore abandoned buildings, tunnels, and other man-made structures reclaimed by nature. Beyond the thrill of exploration, urbex enthusiasts seek to uncover forgotten histories and capture the haunting beauty of decay through photography and storytelling. From derelict factories to forgotten amusement parks, these hidden gems offer glimpses into the past while igniting imaginations with tales of bygone eras and the passage of time.",
                "As artificial intelligence (AI) continues to permeate every aspect of society, ethical considerations surrounding its development and deployment have become increasingly paramount. From algorithmic bias and privacy concerns to the implications of autonomous weapons and job displacement, the ethical landscape of AI is multifaceted and complex. Balancing innovation with accountability, transparency, and human values is essential to ensuring that AI technologies serve the greater good while mitigating potential risks and unintended consequences.",
                "Coral reefs, often referred to as the rainforests of the sea, harbor unparalleled biodiversity and provide crucial ecosystem services to marine life and coastal communities. Despite covering less than 1% of the ocean floor, coral reefs support over 25% of marine species, making them vital hubs of ecological activity. However, these fragile ecosystems face myriad threats, including climate change, overfishing, pollution, and ocean acidification, underscoring the urgent need for conservation efforts to preserve these underwater wonders for future generations.",
                "Procrastination, the tendency to delay or avoid tasks despite knowing the negative consequences, is a pervasive phenomenon that affects individuals across all walks of life. From students cramming for exams to professionals procrastinating on important projects, understanding the psychological mechanisms behind procrastination is crucial for effective self-management and productivity. Factors such as fear of failure, perfectionism, impulsivity, and poor time management skills contribute to procrastination, highlighting the importance of adopting strategies to overcome this common behavioral pattern.",
                "In an age dominated by digital streaming and downloads, vinyl records have experienced a remarkable resurgence, captivating audiophiles and music enthusiasts alike with their warm, analog sound and tactile appeal. Beyond nostalgia, vinyl records offer a tangible connection to the music, artwork, and culture of bygone eras, fostering a sense of intimacy and authenticity in an increasingly digital world. From collectors seeking rare gems to artists releasing limited-edition vinyl albums, the vinyl revival is a testament to the enduring allure of analog audio in the digital age.");

        trainingSentences.forEach(s -> generator.train(s));
    }

    private void greetUser() {
        consoleManager.printDecoratedMessage(String.format("Welcome %s!", userName), "-", true);
    }

    private String getUserName() {
        return consoleManager.getResponse("\nEnter your name");
    }

    private void showMainMenu(Player player) throws Exception {
        var options = List.of("view stats", "new session", "quit");
        var resp = consoleManager.getResponse("\nWhat would you like to do to?'", options);

        if (resp.equalsIgnoreCase(options.get(0))) {
            viewOverallStats(player);
        } else if (resp.equalsIgnoreCase(options.get(1))) {
            startNewSession(player);
        } else if (resp.equalsIgnoreCase(options.get(2))) {
            System.exit(0);
        }
    }

    private void viewOverallStats(Player player) {
        consoleManager.printDecoratedMessage(Player.formatTerminalString(player), "*", true);
        consoleManager.colorPrint("\nAll Sessions", consoleManager.defaultColor, true);
        consoleManager.printDecoratedMessage(GameSession.formatTerminalString(player.getGameSessions()), "-",
                false);
    }

    private void startNewSession(Player player) throws Exception {
        if (!generator.isTrained())
            trainSentenceGenerator();

        String text = generator.generateSentence(defaultNoOfWords);
        gameSession = new Game(text, player);

        gameSession.start();
    }

    private void welcome() {
        consoleManager.printDecoratedMessage("Welcome to ConsoleMonkey!", "#", true);
    }
}
