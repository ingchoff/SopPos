package com.project.invoice.services;

import com.project.invoice.entities.Income;
import com.project.invoice.entities.Invoice;
import com.project.invoice.entities.OrderlistModel;
import com.project.invoice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    private DiscoveryClient discoveryClient;
    public InvoiceService(InvoiceRepository repo) {
        this.invoiceRepository = repo;
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        Invoice result = invoiceRepository.save(invoice);
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("orderservice");
        String serviceUri = String.format("%s/api/order/update", instances.get(0).getUri().toString());
        restTemplate.postForObject(serviceUri, invoice, Invoice.class);
        Income income = new Income();
        income.setInvoice_id(result.getId());
        income.setPrice(500.00);
        RestTemplate rt = new RestTemplate();
        List<ServiceInstance> orderInstance = discoveryClient.getInstances("accountservice");
        String uri = String.format("%s/api/income/create", orderInstance.get(0).getUri().toString());
        rt.postForObject(uri, income, Income.class);
        return result;
    }
}
