package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {    
    private static final Logger log = LogManager.getLogger(SalesSystemModel.class);
    // Warehouse model
    private StockTableModel warehouseTableModel;   
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;   
    // History table model
    private HistoryTableModel historyTableModel;
    
    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;       
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        historyTableModel = new HistoryTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        historyTableModel.populateWithData(domainController.loadSalesHistory());
    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
    public HistoryTableModel getHistoryTableModel(){
    	return historyTableModel;
    }
}
