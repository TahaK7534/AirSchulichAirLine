CREATE DATABASE IF NOT EXISTS airSchulich;
USE airSchulich;


CREATE TABLE IF NOT EXISTS user_table (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    credit_card_number VARCHAR(255),  -- New column for credit card number
    cvv VARCHAR(255), 
    money DECIMAL(10, 2) NOT NULL

);


CREATE TABLE IF NOT EXISTS flight_table (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(255) NOT NULL,
    departure_date DATE NOT NULL,
    return_date DATE NOT NULL,
    price_range INT NOT NULL,
    departure_location VARCHAR(255) NOT NULL,
    arrival_location VARCHAR(255) NOT NULL,
    departure_time VARCHAR(255) NOT NULL,
    arrival_time VARCHAR(255) NOT NULL,
    num_of_business_seats INT NOT NULL,
    num_of_comfort_seats INT NOT NULL,
    num_of_ordinary_seats INT NOT NULL
);

CREATE TABLE IF NOT EXISTS seat_availability (
    flight_id INT,
    seat_number INT,
    is_available BOOLEAN,
    passenger_name VARCHAR(255),  -- New column for passenger name
    insurance BOOLEAN,
    PRIMARY KEY (flight_id, seat_number),
    FOREIGN KEY (flight_id) REFERENCES flight_table(flight_id)  -- Corrected reference to flight_table
);

CREATE TABLE IF NOT EXISTS promotions_table (
    month_id INT AUTO_INCREMENT PRIMARY KEY,
    the_month VARCHAR(255) NOT NULL,
    the_info VARCHAR(255) NOT NULL,
    percentage DECIMAL(5,2) NOT NULL
);


-- INSERT INTO promotions_table (the_month, the_info, percentage)
-- VALUES
--     ('January', 'Start the new year with joy! Enjoy exclusive in-flight entertainment options and a complimentary travel guide on all January flights. 5% off', 5.00),
--     ('February', 'Embrace the winter magic! Cozy up with a hot beverage and receive a free winter travel kit, including a blanket and travel-sized toiletries. 7.5% off ', 7.50),
--     ('March', 'Spring into travel! Book in March and receive a special spring-themed snack box filled with delicious treats. 6.25% off', 6.25),
--     ('April', 'April showers bring travel rewards! All April bookings come with a free rain-resistant travel pouch for your essentials. 4.75% off', 4.75),
--     ('May', 'Blossom with our May promo! Receive a floral-scented travel candle and a personalized thank-you card for choosing us. 8.2% off', 8.20),
--     ('June', 'Summer vibes are here! Book a June flight and get a free reusable travel water bottle to stay hydrated on your journeys. 3.5% off', 3.50),
--     ('July', 'Celebrate Independence Day with us! Enjoy a complimentary patriotic-themed snack box on all July flights. 6.75% off', 6.75),
--     ('August', 'Back-to-school, back to travel! Book in August and receive a stylish backpack as a travel companion. 5.9% off', 5.80),
--     ('September', 'Fall in love with travel! All September bookings include a free autumn-themed photo book to capture your memories. 4.9% off', 4.90),
--     ('October', 'Spooktacular October awaits! Get a free Halloween-themed travel pillow to make your journey extra cozy. 7.10% off', 7.10),
--     ('November', 'Thanksgiving travel treats! Book in November and receive a complimentary snack box filled with festive goodies. 5.25% off', 5.25),
--     ('December', 'Deck the halls with our December promo! Enjoy a holiday-themed in-flight experience with special decorations and treats. 9% off', 9.00);
--     








-- Insert a new user with credit card information
-- INSERT INTO user_table (username, password, email, role, credit_card_number, cvv, money)
-- VALUES 
-- ('Admin', '1', 'Admin@gmail.com', 'Admin', '1234567890123456', '123', 1000),
-- ('FA', '1', 'FlightAttendent@gmail.com', 'flight attendants', '1234567890123456', '123', 1000),
-- ('AirLineA', '1', 'airlineAgents@gmail.com', 'airlineAgents', '1234567890123456', '123', 1000);




-- View the data
SELECT * FROM flight_table;
SELECT * FROM seat_availability;
select * From user_table;
SELECT * FROM promotions_table;
