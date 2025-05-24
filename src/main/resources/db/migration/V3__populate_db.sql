INSERT INTO users (email, password) VALUES ('john.doe@example.com', 'hashed_password1'),
('jane.smith@example.com', 'hashed_password2'),
('bob.jones@example.com', 'hashed_password3');

-- Insert sample data into asset table
INSERT INTO asset (name, ticker, isin, asset_type, description) VALUES
('Apple Inc.', 'AAPL', 'US0378331005', 'EQUITY', 'Technology company'),
('Tesla, Inc.', 'TSLA', 'US88160R1014', 'EQUITY', 'Electric vehicle manufacturer'),
('SPDR S&P 500 ETF', 'SPY', 'US78462F1030', 'ETF', 'Tracks S&P 500 index'),
('Microsoft Corporation', 'MSFT', 'US5949181045', 'EQUITY', 'Software and cloud services'),
('Vanguard Total Bond Market ETF', 'BND', 'US9219378356', 'ETF', 'Broad-based bond ETF');

-- Insert sample data into profile table
INSERT INTO profile (id, first_name, last_name, company) VALUES
(1, 'John', 'Doe', 'Tech Corp'),
(2, 'Jane', 'Smith', 'Finance Inc'),
(3, 'Bob', 'Jones', 'Consulting LLC');

-- Insert sample data into portfolio table
INSERT INTO portfolio (name, users_id) VALUES
('Growth Portfolio', 1),
('Dividend Portfolio', 1),
('Balanced Portfolio', 2),
('Tech Portfolio', 3);

-- Insert sample data into asset_List table
INSERT INTO asset_List (portfolio_id, asset_id) VALUES
(1, 1), -- Growth Portfolio: Apple
(1, 2), -- Growth Portfolio: Tesla
(2, 3), -- Dividend Portfolio: SPY
(3, 3), -- Balanced Portfolio: SPY
(3, 5), -- Balanced Portfolio: BND
(4, 1), -- Tech Portfolio: Apple
(4, 4); -- Tech Portfolio: Microsoft